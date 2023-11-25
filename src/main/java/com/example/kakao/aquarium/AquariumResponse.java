package com.example.kakao.aquarium;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.ScheduleEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.board.Board;
import com.example.kakao.book.Book;
import com.example.kakao.fish.Fish;
import com.example.kakao.schedule.Schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class AquariumResponse {



    
    @Getter
    @Setter
    @ToString
    public static class AquariumDTO {
        private int id;
        private String title;
        private String intro;
        private String photo;
        private Boolean isFreshWater;
        private String size; // 길이/폭/높이
        private String s1;
        private String s2;
        private String s3;
        private String s4;
        private String s5;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private List<FishDTO> fishDTOList;
        private List<ScheduleDTO> scheduleDTOList;
        // private List<DiaryDTO> diaryDTOList;

        public AquariumDTO(Aquarium aquarium) {
            this.id = aquarium.getId();
            this.title = aquarium.getTitle();
            this.intro = aquarium.getIntro();
            this.photo = aquarium.getPhoto();
            this.isFreshWater = aquarium.getIsFreshWater();
            this.size = aquarium.getSize();
            this.s1 = aquarium.getS1();
            this.s2 = aquarium.getS2();
            this.s3 = aquarium.getS3();
            this.s4 = aquarium.getS4();
            this.s5 = aquarium.getS5();
            this.createdAt = aquarium.getCreatedAt();
            this.updatedAt = aquarium.getUpdatedAt();
            this.fishDTOList = aquarium.getFishList().stream()
                    .map(fish -> new FishDTO(fish))
                    .collect(Collectors.toList());
            this.scheduleDTOList = aquarium.getScheduleList().stream()
                    .map(schedule -> new ScheduleDTO(schedule))
                    .collect(Collectors.toList());
        }
    }




    @Getter
    @Setter
    @ToString
    public static class FishDTO {
        private int id;
        private int aquariumId;
        private Book book;
        private FishClassEnum fishClassEnum;
        private String name;
        private String text;
        private Integer quantity;
        private Integer gender;
        private String photo;
        private String price;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public FishDTO(Fish fish) {
            this.id = fish.getId();
            this.aquariumId = fish.getAquarium().getId();
            this.book = fish.getBook();
            this.fishClassEnum = fish.getFishClassEnum();
            this.name = fish.getName();
            this.text = fish.getText();
            this.quantity = fish.getQuantity();
            this.gender = fish.getGender();
            this.photo = fish.getPhoto();
            this.price = fish.getPhoto();
            this.createdAt = fish.getCreatedAt();
            this.updatedAt = fish.getUpdatedAt();
        }
    }




    @Getter
    @Setter
    @ToString
    public static class ScheduleDTO {
        private int id;
        private int aquariumId;
        private String aquariumTitle;
        private String title;
        private ScheduleEnum scheduleEnum;
        private Boolean isCompleted;
        private Integer betweenDay;
        private Timestamp targetDay;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public ScheduleDTO(Schedule schedule) {
            this.id = schedule.getId();
            this.aquariumId = schedule.getAquarium().getId();
            this.aquariumTitle = schedule.getAquarium().getTitle();
            this.title = schedule.getTitle();
            this.scheduleEnum = schedule.getScheduleEnum();
            this.betweenDay = schedule.getBetweenDay();
            this.targetDay = schedule.getTargetDay();
            this.isCompleted = schedule.getIsCompleted();
            this.createdAt = schedule.getCreatedAt();
            this.updatedAt = schedule.getUpdatedAt();
        }
    }




}