package com.example.kakao.schedule;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final HttpSession session;






    // 스케줄 체크
    @PostMapping("/schedules/check/{scheduleId}")
    public ResponseEntity<?> check(@PathVariable int scheduleId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.ScheduleDTO responseDTO = scheduleService.check(sessionUser.getId(), scheduleId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


    // 스케줄 체크 취소
    @PostMapping("/schedules/checkcancel/{scheduleId}")
    public ResponseEntity<?> checkCancel(@PathVariable int scheduleId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.ScheduleDTO responseDTO = scheduleService.checkCancel(sessionUser.getId(), scheduleId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



    // 스케줄 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> delete(@PathVariable int scheduleId) {
        
        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.ScheduleDTO responseDTO = scheduleService.delete(sessionUser.getId(), scheduleId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



    // 스케줄 추가
    @PostMapping("/schedules/{aquariumId}")
    public ResponseEntity<?> create(@RequestBody @Valid ScheduleRequest.CreateDTO requestDTO, Errors errors, @PathVariable int aquariumId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.ScheduleDTO responseDTO = scheduleService.create(requestDTO, sessionUser.getId(), aquariumId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



}
