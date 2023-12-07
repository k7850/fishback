package com.example.kakao.comment;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    private final HttpSession session;



    // 댓글 작성
    @PostMapping("/comments/{boardId}")
    public ResponseEntity<?> create(@RequestBody @Valid CommentRequest.CommentDTO requestDTO, Errors errors, @PathVariable int boardId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        CommentResponse.CommentDTO responseDTO = commentService.create(requestDTO, sessionUser, boardId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> delete(@PathVariable int commentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        CommentResponse.CommentDTO responseDTO = commentService.delete(sessionUser.getId(), commentId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 댓글 좋아요
    @PostMapping("/comments/like/{commentId}")
    public ResponseEntity<?> like(@PathVariable int commentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        CommentResponse.CommentDTO responseDTO = commentService.like(sessionUser, commentId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



    // 댓글 싫어요
    @PostMapping("/comments/dislike/{commentId}")
    public ResponseEntity<?> dislike(@PathVariable int commentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        CommentResponse.CommentDTO responseDTO = commentService.dislike(sessionUser, commentId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 댓글 좋아요/싫어요 삭제
    @DeleteMapping("/comments/likecancel/{commentId}")
    public ResponseEntity<?> likecancel(@PathVariable int commentId) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        CommentResponse.CommentDTO responseDTO = commentService.likecancel(sessionUser, commentId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }





}
