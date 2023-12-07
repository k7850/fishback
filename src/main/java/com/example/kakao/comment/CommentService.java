package com.example.kakao.comment;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.LikeComment;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao._repository.LikeCommentRepository;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.book.Book;
import com.example.kakao.book.BookRepository;
import com.example.kakao.comment.Comment;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final LikeCommentRepository likeCommentRepository;




    // 댓글 작성
    @Transactional
    public CommentResponse.CommentDTO create(CommentRequest.CommentDTO requestDTO, User sessionUser, int boardId) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404(boardId + "없음"));

        Comment comment = Comment.builder()
                .text(requestDTO.getText())
                .user(sessionUser)
                .board(board)
                .isDelete(false)
                .likeCommentList(new ArrayList<LikeComment>())
                .build();

        commentRepository.save(comment);

        
        CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());

        return responseDTO;
    }

    

    
    // 댓글 삭제
    @Transactional
    public CommentResponse.CommentDTO delete(int sessionUserId, int commentId) {
        
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception404(commentId + "없음"));
        
        if(comment.getIsDelete() == true){
            throw new Exception400(commentId + "이미삭제상태");
        }

        if(comment.getUser().getId() != sessionUserId){
            throw new Exception400(commentId + "내댓글아님");
        }

        comment.setIsDelete(true);

        CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUserId);

        return responseDTO;
    }





    // 댓글 좋아요
    @Transactional
    public CommentResponse.CommentDTO like(User sessionUser, int commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception404(commentId + "없음"));

        if(comment.getIsDelete()==true){
            throw new Exception400("삭제댓글임");
        }

        Optional<LikeComment> OptionalLc = likeCommentRepository.findByUserIdAndCommentId(sessionUser.getId(), commentId);

        if (OptionalLc.isPresent()) {
            LikeComment lc = OptionalLc.get();

            if (lc.getIsLike() == null || lc.getIsLike() == false) {
                lc.setIsLike(true);
                CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());
                return responseDTO;
            }
            throw new Exception400("이미했음");
        }

        LikeComment lc = LikeComment.builder()
                .user(sessionUser)
                .comment(comment)
                .isLike(true)
                .build();

        try {
            likeCommentRepository.save(lc);
        } catch (Exception e) {
            throw new Exception400("이미했음");
        }

        CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());
        return responseDTO;
    }







    // 댓글 싫어요
    @Transactional
    public CommentResponse.CommentDTO dislike(User sessionUser, int commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception404(commentId + "없음"));

        if(comment.getIsDelete()==true){
            throw new Exception400("삭제댓글임");
        }

        Optional<LikeComment> OptionalLc = likeCommentRepository.findByUserIdAndCommentId(sessionUser.getId(), commentId);

        if (OptionalLc.isPresent()) {
            LikeComment lc = OptionalLc.get();

            if (lc.getIsLike() == null || lc.getIsLike() == true) {
                lc.setIsLike(false);
                CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());
                return responseDTO;
            }
            throw new Exception400("이미했음");
        }

        LikeComment lc = LikeComment.builder()
                .user(sessionUser)
                .comment(comment)
                .isLike(false)
                .build();

        try {
            likeCommentRepository.save(lc);
        } catch (Exception e) {
            throw new Exception400("이미했음");
        }

        CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());
        return responseDTO;
    }





    // 댓글 좋아요/싫어요 삭제
    @Transactional
    public CommentResponse.CommentDTO likecancel(User sessionUser, int commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception404(commentId + "없음"));

        if(comment.getIsDelete()==true){
            throw new Exception400("삭제댓글임");
        }

        LikeComment lc = likeCommentRepository.findByUserIdAndCommentId(sessionUser.getId(), commentId)
                .orElseThrow(() -> new Exception404("이미했음"));


        CommentResponse.CommentDTO responseDTO = new CommentResponse.CommentDTO(comment, sessionUser.getId());

        responseDTO.setIsMyLike(false);
        responseDTO.setIsMyDislike(false);
        if(lc.getIsLike() == true){
            responseDTO.setLikeCommentCount(responseDTO.getLikeCommentCount()-1);
        } else{
            responseDTO.setDislikeCommentCount(responseDTO.getDislikeCommentCount()-1);
        }

        likeCommentRepository.deleteById(lc.getId());

        return responseDTO;
    }









}