package com.example.kakao.board;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.BoardEmoticon;
import com.example.kakao._entity.BoardPhoto;
import com.example.kakao._entity.enums.EmoticonEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao._repository.BoardEmoticonRepository;
import com.example.kakao._repository.BoardPhotoRepository;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.book.Book;
import com.example.kakao.comment.Comment;
import com.example.kakao.comment.CommentResponse;
import com.example.kakao.fish.Fish;
import com.example.kakao.fish.FishRepository;
import com.example.kakao.fish.FishRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final AquariumRepository aquariumRepository;
    private final BoardEmoticonRepository boardEmoticonRepository;
    private final FishRepository fishRepository;
    private final BoardPhotoRepository boardPhotoRepository;





    // 보드 메인
    public List<BoardResponse.BoardMainDTO> main(int sessiunUserId, String keyword, Integer page) {
        
        // List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Order.desc("id")));

        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");

        // List<Board> boardList = boardRepository.findAll(pageable);
        // Page<Board> boardPage = boardRepository.findAll(pageable);
        Page<Board> boardPage = boardRepository.findByTitleContainingOrTextContaining(keyword, keyword, pageable);
        List<Board> boardList = boardPage.getContent();

        // System.out.println(page);
        // System.out.println("=====================");
        // System.out.println(boardPage.getContent());
        // System.out.println("=====================");
        // System.out.println(boardPage.getNumber());
        // System.out.println(boardPage.getNumberOfElements());
        // System.out.println(boardPage.getSize());
        // System.out.println("=====================");
        // System.out.println(boardPage.getTotalElements());
        // System.out.println(boardPage.getTotalPages());
        // System.out.println(boardPage.getPageable());
        // System.out.println(boardPage.getSort());
        // System.out.println(boardPage.get());
        // System.out.println("=====================");
        // System.out.println(boardPage.hasNext());
        // System.out.println(boardPage.hasPrevious());
        // System.out.println("=====================");
        // System.out.println(boardPage.isEmpty());
        // System.out.println(boardPage.isFirst());
        // System.out.println(boardPage.isLast());
        // System.out.println("=====================");


        List<BoardResponse.BoardMainDTO> responseDTOList = boardList.stream()
                .map(board -> new BoardResponse.BoardMainDTO(board, sessiunUserId))
                .collect(Collectors.toList());
        
        return responseDTOList;
    }



    // // 보드 메인
    // public List<BoardResponse.BoardMainDTO> main(int sessiunUserId) {
        
    //     List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Order.desc("id")));

    //     List<BoardResponse.BoardMainDTO> responseDTOList = boardList.stream()
    //             .map(board -> new BoardResponse.BoardMainDTO(board, sessiunUserId))
    //             .collect(Collectors.toList());
        
    //     return responseDTOList;
    // }


    // 보드 디테일
    @Transactional
    public BoardResponse.BoardDTO detail(User sessionUser, int boardId) {

        Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new Exception404(boardId + "없음"));

        board.setViewCount(board.getViewCount() + 1);

        BoardResponse.BoardDTO responseDTO = new BoardResponse.BoardDTO(board, sessionUser.getId());
        
        BoardEmoticon boardEmoticon = boardEmoticonRepository.findByUserIdAndBoardId(sessionUser.getId(), boardId);

        if(boardEmoticon == null){
            boardEmoticon = BoardEmoticon
                .builder()
                .user(sessionUser)
                .board(board)
                .build();
                
            boardEmoticonRepository.save(boardEmoticon);
        }
        
        return responseDTO;
    }



    // 보드 추가
    @Transactional
    public BoardResponse.BoardDTO create(BoardRequest.BoardDTO requestDTO, User sessionUser, MultipartFile videoFile, List<MultipartFile> imageFileList) {

        Board board = Board.builder()
            .user(sessionUser)
            .title(requestDTO.getTitle())
            .text(requestDTO.getText())
            .viewCount(0)
            .commentList(new ArrayList<>())
            .boardPhotoList(new ArrayList<>())
            .boardEmoticonList(new ArrayList<>())
            .build();

        if(requestDTO.getAquariumId() >= 0){
            Aquarium aquarium = aquariumRepository.findById(requestDTO.getAquariumId())
                    .orElseThrow(() -> new Exception404(requestDTO.getAquariumId() + "없음"));

            if(aquarium.getUser().getId() != sessionUser.getId()){
                throw new Exception403(requestDTO.getAquariumId() + "권한없음");
            }
            board.setAquarium(aquarium);
        }

        if(requestDTO.getFishId() >= 0){
            Fish fish = fishRepository.findById(requestDTO.getFishId())
                    .orElseThrow(() -> new Exception404(requestDTO.getFishId() + "없음"));

            if(fish.getAquarium().getUser().getId() != sessionUser.getId()){
                throw new Exception403(requestDTO.getFishId() + "권한없음");
            }
            board.setFish(fish);
        }

        if(videoFile != null && videoFile.getSize() != 0){
            board.setVideo(ImageUtils.updateVideo(videoFile));
        }

        boardRepository.save(board);


        if(imageFileList != null && imageFileList.size() != 0 && imageFileList.get(0).getSize() != 0){
            List<BoardPhoto> boardPhotoList = new ArrayList<>();
            
            for (MultipartFile imageFile : imageFileList) {
                String wherePath = "boardPhoto/" + ImageUtils.updateImage(imageFile, "boardPhoto/");
                BoardPhoto boardPhoto = BoardPhoto.builder()
                        .board(board)
                        .photo(wherePath)
                        .build();
                boardPhotoList.add(boardPhoto);
            }

            boardPhotoRepository.saveAll(boardPhotoList);
            board.setBoardPhotoList(boardPhotoList);
        }

        // Board board2 = boardRepository.findById(board.getId()).get();
        
        BoardResponse.BoardDTO responseDTO = new BoardResponse.BoardDTO(board, sessionUser.getId());
        return responseDTO;
    }
    


    // 보드 삭제
    @Transactional
    public BoardResponse.BoardMainDTO delete(int sessionUserId, int boardId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404(boardId + "없음"));

        if(board.getUser().getId() != sessionUserId){
            throw new Exception403(boardId + "권한없음");
        }
        
        boardRepository.deleteById(board.getId());

        BoardResponse.BoardMainDTO responseDTO = new BoardResponse.BoardMainDTO(board, sessionUserId);
        return responseDTO;
    }




    // 이모티콘 삭제
    @Transactional
    public BoardResponse.EmoticonDTO emoticonDelete(int sessionUserId, int boardId, EmoticonEnum emoticonEnum) {
        
        BoardEmoticon boardEmoticon = boardEmoticonRepository.findByUserIdAndBoardId(sessionUserId, boardId);

        // if(boardEmoticon.getEmoticonEnum() != emoticonEnum){
        //     throw new Exception400(emoticonEnum + "오류");
        // }

        boardEmoticon.setEmoticonEnum(null);

        BoardResponse.EmoticonDTO responseDTO = new BoardResponse.EmoticonDTO();
        responseDTO.setDeleteEmoticon(emoticonEnum);

        return responseDTO;
    }


    // 이모티콘
    @Transactional
    public BoardResponse.EmoticonDTO emoticon(int sessionUserId, int boardId, EmoticonEnum emoticonEnum) {
        
        BoardEmoticon boardEmoticon = boardEmoticonRepository.findByUserIdAndBoardId(sessionUserId, boardId);

        // if(boardEmoticon.getEmoticonEnum() != emoticonEnum){
        //     throw new Exception400(emoticonEnum + "오류");
        // }

        BoardResponse.EmoticonDTO responseDTO = new BoardResponse.EmoticonDTO();
        responseDTO.setDeleteEmoticon(boardEmoticon.getEmoticonEnum());
        responseDTO.setCreateEmoticon(emoticonEnum);

        boardEmoticon.setEmoticonEnum(emoticonEnum);

        return responseDTO;
    }











}