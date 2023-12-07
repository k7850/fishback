package com.example.kakao.board;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.BoardEmoticon;
import com.example.kakao._entity.BoardPhoto;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.comment.Comment;
import com.example.kakao.fish.Fish;
import com.example.kakao.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board_tb")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aquarium aquarium;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fish fish;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createdAt DESC") // 코멘트 최근 순서대로 정렬
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardPhoto> boardPhotoList = new ArrayList<>();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardEmoticon> boardEmoticonList = new ArrayList<>();

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 500)
    private String text;

    private String video;

    @ColumnDefault("0")
    private Integer viewCount;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;







    @Builder
    public Board(int id, User user, Aquarium aquarium, Fish fish, List<Comment> commentList,
            List<BoardPhoto> boardPhotoList, List<BoardEmoticon> boardEmoticonList, String title, String text,
            String video, Integer viewCount, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.aquarium = aquarium;
        this.fish = fish;
        this.commentList = commentList;
        this.boardPhotoList = boardPhotoList;
        this.boardEmoticonList = boardEmoticonList;
        this.title = title;
        this.text = text;
        this.video = video;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    



}
