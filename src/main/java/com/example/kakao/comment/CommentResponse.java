package com.example.kakao.comment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.kakao._entity.BoardEmoticon;
import com.example.kakao._entity.Equipment;
import com.example.kakao._entity.enums.EmoticonEnum;
import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.book.Book;
import com.example.kakao.comment.Comment;
import com.example.kakao.fish.Fish;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CommentResponse {




    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class CommentDTO {
        private int id;
        private int boardId;
        private int userId;
        private String username;
        private String text;
        private Boolean isDelete;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        private Integer likeCommentCount;
        private Integer dislikeCommentCount;
        private Boolean isMyLike = false;
        private Boolean isMyDislike = false;


        public CommentDTO(Comment comment, int sessionUserId) {
            this.id = comment.getId();
            this.boardId = comment.getBoard().getId();
            this.userId = comment.getUser().getId();
            this.username = comment.getUser().getUsername();
            this.text = comment.getText();
            this.isDelete = comment.getIsDelete();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();

            this.likeCommentCount = comment.getLikeCommentList().stream()
                    .map(t -> (t.getIsLike() == true) ? 1 : 0)
                    .reduce(0, (a, b) -> a + b);

            this.dislikeCommentCount = comment.getLikeCommentList().stream()
                    .map(t -> (t.getIsLike() == false) ? 1 : 0)
                    .reduce(0, (a, b) -> a + b);

            comment.getLikeCommentList().stream()
                    .filter(likeComment -> likeComment.getUser().getId() == sessionUserId)
                    .findFirst()
                    .ifPresent(likeComment -> {
                        if (likeComment.getIsLike() == true) {
                            this.isMyLike = true;
                        } else {
                            this.isMyDislike = true;
                        }
                    });
        }
    }




}
