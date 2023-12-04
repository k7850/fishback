package com.example.kakao.comment;

import com.example.kakao._entity.LikeComment;
import com.example.kakao.board.Board;
import com.example.kakao.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@ToString
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment_tb")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<LikeComment> likeCommentList = new ArrayList<>();

    @ColumnDefault("false")
    private Boolean isDelete;

    @Column(length = 500, nullable = false)
    private String text;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;





    @Builder
    public Comment(Integer id, User user, List<LikeComment> likeCommentList, Boolean isDelete, String text,
            Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.likeCommentList = likeCommentList;
        this.isDelete = isDelete;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    



}
