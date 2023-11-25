package com.example.kakao.board;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.BoardPhoto;
import com.example.kakao.aquarium.Aquarium;
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
    private Aquarium aquarium;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardPhoto> boardPhotoList = new ArrayList<>();

    @Column(length = 30, nullable = false)
    private String title;

    private String text;

    private String video;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;






    @Builder
    public Board(int id, Aquarium aquarium, User user, List<BoardPhoto> boardPhotoList, String title, String text,
            String video, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.aquarium = aquarium;
        this.user = user;
        this.boardPhotoList = boardPhotoList;
        this.title = title;
        this.text = text;
        this.video = video;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    



    
    
    


}
