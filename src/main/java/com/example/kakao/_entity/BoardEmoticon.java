package com.example.kakao._entity;

import com.example.kakao._entity.enums.EmoticonEnum;
import com.example.kakao.board.Board;
import com.example.kakao.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@ToString
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board_emoticon_tb")
public class BoardEmoticon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Enumerated(EnumType.STRING)
    private EmoticonEnum emoticonEnum;

    @CreationTimestamp
    private Timestamp createdAt;







    
    @Builder
    public BoardEmoticon(int id, User user, Board board, EmoticonEnum emoticonEnum, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.board = board;
        this.emoticonEnum = emoticonEnum;
        this.createdAt = createdAt;
    }



    

    


    


}
