package com.example.kakao.comment;

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


public class CommentRequest {









    @Getter
    @Setter
    @ToString
    public static class CommentDTO {

        @NotEmpty
        @Size(min = 5, max = 500, message = "5에서 500자 이내여야 합니다.")
        private String text;
        

        public Comment toEntity() {
            return Comment.builder()
                    .text(text)
                    .build();
        }
    }






}
