package com.example.kakao.schedule;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

import javax.validation.constraints.*;

import com.example.kakao._entity.enums.ScheduleEnum;
import com.example.kakao.aquarium.Aquarium;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ScheduleRequest {
    @Getter
    @Setter
    @ToString
    public static class CreateDTO {

        @NotEmpty
        @Size(min = 1, max = 20, message = "1에서 20자 이내여야 합니다.")
        private String title;

        @NotEmpty
        @Pattern(regexp = "지정|요일|간격", message = "지정, 요일, 간격 중에서")
        private String scheduleEnum;

        private Integer betweenDay;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private Timestamp targetDay;

        @Min(1)
        @Max(3)
        private Integer importantly;

        public Schedule toEntity(Aquarium aquarium) {
            return Schedule.builder()
                    .aquarium(aquarium)
                    .title(title)
                    .scheduleEnum(ScheduleEnum.valueOf(scheduleEnum))
                    .betweenDay(betweenDay)
                    .targetDay(targetDay)
                    .isCompleted(false)
                    .importantly(importantly)
                    .build();
        }
    }



}
