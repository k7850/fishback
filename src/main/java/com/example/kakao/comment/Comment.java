package com.example.kakao.comment;

import com.example.kakao._entity.LikeComment;
import com.example.kakao.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

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

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY)
    private List<LikeComment> likeCommentList = new ArrayList<>();

    @ColumnDefault("false")
    private Boolean isDelete;

    @Column(length = 500, nullable = false)
    private String text;

    @CreationTimestamp
    private Timestamp createdAt;


    // @Builder
    // public Comment(Integer id, User user, Episode episode, List<LikeComment> likeCommentList,
    //                List<ReComment> reCommentList, Boolean isDelete, String text, Timestamp createdAt) {
    //     this.id = id;
    //     this.user = user;
    //     this.episode = episode;
    //     this.likeCommentList = likeCommentList;
    //     this.reCommentList = reCommentList;
    //     this.isDelete = isDelete;
    //     this.text = text;
    //     this.createdAt = createdAt;
    // }



}
