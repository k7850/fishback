package com.example.kakao.fish;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class FishController {

    private final FishService fishService;
    private final HttpSession session;




    // 생물 추가
    @PostMapping("/fishes/{aquariumId}")
    public ResponseEntity<?> create(FishRequest.FishDTO requestDTO, MultipartFile photoFile, @PathVariable int aquariumId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.FishDTO responseDTO = fishService.create(requestDTO, sessionUser.getId(), aquariumId, photoFile);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 생물 삭제
    @DeleteMapping("/fishes/{fishId}")
    public ResponseEntity<?> delete(@PathVariable int fishId) {
        
        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.FishDTO responseDTO = fishService.delete(sessionUser.getId(), fishId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 생물 어항 이동
    @PatchMapping("/fishes/{fishId}/{aquariumId}")
    public ResponseEntity<?> move(@PathVariable int fishId, @PathVariable int aquariumId) {

        User sessionUser = (User) session.getAttribute("sessionUser");

        AquariumResponse.FishDTO responseDTO = fishService.move(sessionUser.getId(), fishId, aquariumId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }




    // 생물 수정
    @PutMapping("/fishes/{fishId}/{aquariumId}")
    public ResponseEntity<?> update(FishRequest.FishDTO requestDTO, MultipartFile photoFile, @PathVariable int fishId, @PathVariable int aquariumId) {

        System.out.println("requestDTO : "+requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        AquariumResponse.FishDTO responseDTO = fishService.update(requestDTO, sessionUser.getId(), fishId, aquariumId, photoFile);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }





}
