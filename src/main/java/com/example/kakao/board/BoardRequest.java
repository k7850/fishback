package com.example.kakao.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.ScheduleEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.book.Book;
import com.fasterxml.jackson.annotation.JsonFormat;


public class BoardRequest {









    @Getter
    @Setter
    @ToString
    public static class BoardDTO {

        private String title;

        private String text;

        private int aquariumId;

        private int fishId;


        // public Board toEntity(Aquarium aquarium, Book book) {
        //     return Fish.builder()
        //             .photo(photo)
        //             .aquarium(aquarium)
        //             .fishClassEnum(FishClassEnum.valueOf(fishClassEnum))
        //             .name(name)
        //             .text(text)
        //             .quantity(quantity)
        //             .isMale(isMale)
        //             .price(price)
        //             .book(book)
        //             .build();
        // }
    }



}
