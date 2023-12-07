package com.example.kakao.board;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao._entity.enums.EmoticonEnum;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.comment.CommentResponse;
import com.example.kakao.fish.FishRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;



    // // 보드 메인
    // @GetMapping("/boards")
    // public ResponseEntity<?> main(@RequestParam(defaultValue = "") String keyword,
    //         @RequestParam(name = "pageSearchAll", defaultValue = "0") Integer page) {
                
    //     keyword = keyword.trim();




    //     User sessionUser = (User) session.getAttribute("sessionUser");

    //     List<BoardResponse.BoardMainDTO> responseDTOList = boardService.main(sessionUser.getId());

    //     // return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    // }


    // 보드 메인
    @GetMapping("/boards")
    public ResponseEntity<?> main(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0") Integer page) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<BoardResponse.BoardMainDTO> responseDTOList = boardService.main(sessionUser.getId(), keyword, page);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    }


    // 보드 디테일
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.BoardDTO responseDTO = boardService.detail(sessionUser, boardId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 보드 추가
    @PostMapping("/boards")
    public ResponseEntity<?> create(BoardRequest.BoardDTO requestDTO, MultipartFile videoFile, List<MultipartFile> imageFileList) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.BoardDTO responseDTO = boardService.create(requestDTO, sessionUser, videoFile, imageFileList);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


    // 보드 삭제
    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<?> delete(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.BoardMainDTO responseDTO = boardService.delete(sessionUser.getId(), boardId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }





    // 이모티콘 삭제
    @DeleteMapping("/boards/{boardId}/emoticon/{emoticonEnum}")
    public ResponseEntity<?> emoticonDelete(@PathVariable int boardId, @PathVariable EmoticonEnum emoticonEnum) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.EmoticonDTO responseDTO = boardService.emoticonDelete(sessionUser.getId(), boardId, emoticonEnum);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }

    // 이모티콘
    @PostMapping("/boards/{boardId}/emoticon/{emoticonEnum}")
    public ResponseEntity<?> emoticon(@PathVariable int boardId, @PathVariable EmoticonEnum emoticonEnum) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.EmoticonDTO responseDTO = boardService.emoticon(sessionUser.getId(), boardId, emoticonEnum);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




}
