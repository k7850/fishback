package com.example.kakao.fish;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.book.Book;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "fish_tb")
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Aquarium aquarium;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @Enumerated(EnumType.STRING)
    private FishClassEnum fishClassEnum;

    private String name;

    private String text;

    private Integer quantity;

    private Boolean isMale;

    private String photo;
    
    private String price;
    
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;




    @Builder
    public Fish(int id, Aquarium aquarium, Book book, FishClassEnum fishClassEnum, String name, String text,
            Integer quantity, Boolean isMale, String photo, String price, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.aquarium = aquarium;
        this.book = book;
        this.fishClassEnum = fishClassEnum;
        this.name = name;
        this.text = text;
        this.quantity = quantity;
        this.isMale = isMale;
        this.photo = photo;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    




    


}
