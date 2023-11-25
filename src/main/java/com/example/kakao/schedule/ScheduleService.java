package com.example.kakao.schedule;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.comment.Comment;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final AquariumRepository aquariumRepository;





    
    // 스케줄 체크
    @Transactional
    public AquariumResponse.ScheduleDTO check(int sessionUserId, int scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new Exception404(scheduleId + "없음"));

        if(schedule.getIsCompleted() == true){
            throw new Exception400("이미했음");
        }

        schedule.setIsCompleted(true);

        AquariumResponse.ScheduleDTO responseDTO = new AquariumResponse.ScheduleDTO(schedule);
        return responseDTO;
    }


    
    // 스케줄 체크 취소
    @Transactional
    public AquariumResponse.ScheduleDTO checkCancel(int sessionUserId, int scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new Exception404(scheduleId + "없음"));

        if(schedule.getAquarium().getUser().getId() != sessionUserId){
            throw new Exception403(scheduleId + "권한없음");
        }

        if(schedule.getIsCompleted() == false){
            throw new Exception400(scheduleId + "이미했음");
        }

        schedule.setIsCompleted(false);

        AquariumResponse.ScheduleDTO responseDTO = new AquariumResponse.ScheduleDTO(schedule);
        return responseDTO;
    }


    




}