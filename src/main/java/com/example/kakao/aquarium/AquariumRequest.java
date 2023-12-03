package com.example.kakao.aquarium;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._entity.Diary;
import com.example.kakao._entity.Equipment;
import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.ScheduleEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.book.Book;
import com.fasterxml.jackson.annotation.JsonFormat;


public class AquariumRequest {









    @Getter
    @Setter
    @ToString
    public static class AquariumDTO {

        private String fishClassEnum;

        private String photo;

        @NotEmpty
        @Size(min = 1, max = 20, message = "1에서 20자 이내여야 합니다.")
        private String title;

        private String intro;

        private Boolean isFreshWater;

        private String size;

        List<AquariumRequest.EquipmentDTO> equipmentDTOList;

        private String base64Image;

        // public Aquarium toEntity() {
        //     return Aquarium.builder()
        //             .photo(photo)
        //             .title(title)
        //             .intro(title)
        //             .name(name)
        //             .text(text)
        //             .quantity(quantity)
        //             .isMale(isMale)
        //             .price(price)
        //             .book(book)
        //             .build();
        // }
        
    }





    @Getter
    @Setter
    @ToString
    public static class EquipmentDTO {

        private int id;

        private int aquariumId;

        private String category;

        private String name;

        // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Timestamp createdAt;

        // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Timestamp updatedAt;

        public Equipment toEntity() {
            return Equipment.builder()
                    .id(id)
                    .aquarium(Aquarium.builder().id(aquariumId).build())
                    // .aquarium(aquarium)
                    .category(category)
                    .name(name)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class DiaryDTO {

        private String title;

        private String text;

        private String base64Image;

        // public Diary toEntity(int aquariumId) {
        //     return Diary.builder()
        //             .aquarium(Aquarium.builder().id(aquariumId).build())
        //             .title(title)
        //             .text(text)
        //             .photo(base64Image)
        //             .build();
        // }
    }






}
