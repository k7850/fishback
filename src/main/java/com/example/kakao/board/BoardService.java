package com.example.kakao.board;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.BoardEmoticon;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao._repository.BoardEmoticonRepository;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.comment.Comment;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final AquariumRepository aquariumRepository;
    private final BoardEmoticonRepository boardEmoticonRepository;



    // 보드 메인
    public List<BoardResponse.BoardMainDTO> main(int sessiunUserId) {
        
        List<Board> boardList = boardRepository.findAll();

        List<BoardResponse.BoardMainDTO> responseDTOList = boardList.stream()
                .map(board -> new BoardResponse.BoardMainDTO(board, sessiunUserId))
                .collect(Collectors.toList());
        
        return responseDTOList;
    }


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

    

}