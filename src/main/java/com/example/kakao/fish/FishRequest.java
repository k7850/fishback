package com.example.kakao.fish;

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


public class FishRequest {









    @Getter
    @Setter
    @ToString
    public static class FishDTO {

        private String photo;

        // @NotEmpty
        // @Pattern(regexp = "FISH|PLANT|OTHER", message = "FISH, PLANT, OTHER 중에서")
        private String fishClassEnum;

        // @NotEmpty
        // @Size(min = 1, max = 20, message = "1에서 20자 이내여야 합니다.")
        private String name;

        private String text;

        private Integer quantity;

        private Boolean isMale;

        private String price;

        private int bookId;


        public Fish toEntity(Aquarium aquarium, Book book) {
            return Fish.builder()
                    .photo(photo)
                    .aquarium(aquarium)
                    .fishClassEnum(FishClassEnum.valueOf(fishClassEnum))
                    .name(name)
                    .text(text)
                    .quantity(quantity)
                    .isMale(isMale)
                    .price(price)
                    .book(book)
                    .build();
        }
    }



}
