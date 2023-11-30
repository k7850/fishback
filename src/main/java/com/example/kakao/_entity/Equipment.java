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
@Table(name = "equipment_tb")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Aquarium aquarium;

    private String category;

    private String name;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;



    @Builder
    public Equipment(int id, Aquarium aquarium, String category, String name, Timestamp createdAt,
            Timestamp updatedAt) {
        this.id = id;
        this.aquarium = aquarium;
        this.category = category;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    


}
