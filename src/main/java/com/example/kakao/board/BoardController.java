package com.example.kakao.board;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;





    // 보드 메인
    @GetMapping("/boards")
    public ResponseEntity<?> main() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<BoardResponse.BoardMainDTO> responseDTOList = boardService.main(sessionUser.getId());

        return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    }


    // 보드 디테일
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<?> detail(@PathVariable int boardId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.BoardDTO responseDTO = boardService.detail(sessionUser, boardId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




}
