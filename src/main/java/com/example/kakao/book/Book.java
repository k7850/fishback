package com.example.kakao.book;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.UserTypeEnum;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "book_tb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false)
    private String normalName;

    private String biologyName;

    private Integer difficulty;

    private String photo;
    
    @Column(length = 900)
    private String text;

    @Enumerated(EnumType.STRING)
    private FishClassEnum fishClassEnum;

    private Boolean isFreshWater;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;




    @Builder
    public Book(int id, String normalName, String biologyName, Integer difficulty, String photo, String text,
            FishClassEnum fishClassEnum, Boolean isFreshWater, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.normalName = normalName;
        this.biologyName = biologyName;
        this.difficulty = difficulty;
        this.photo = photo;
        this.text = text;
        this.fishClassEnum = fishClassEnum;
        this.isFreshWater = isFreshWater;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }




    
}
