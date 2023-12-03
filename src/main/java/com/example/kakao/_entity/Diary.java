package com.example.kakao._entity;

import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.board.Board;
import com.example.kakao.comment.Comment;
import com.example.kakao.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "diary_tb")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aquarium aquarium;

    private String title;

    private String text;

    private String photo;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;



    @Builder
    public Diary(int id, Aquarium aquarium, String title, String text, String photo, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.aquarium = aquarium;
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    
    
    
    


}
