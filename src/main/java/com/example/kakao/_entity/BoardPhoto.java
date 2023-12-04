package com.example.kakao._entity;

import com.example.kakao.board.Board;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board_photo_tb")
public class BoardPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String photo;




    @Builder
    public BoardPhoto(int id, Board board, String photo) {
        this.id = id;
        this.board = board;
        this.photo = photo;
    }

    


    


}
