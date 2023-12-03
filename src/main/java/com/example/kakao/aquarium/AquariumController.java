package com.example.kakao.aquarium;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao.fish.FishRequest;
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
public class AquariumController {

    private final AquariumService aquariumService;
    private final HttpSession session;




    // 메인페이지
    @GetMapping("/aquariums")
    public ResponseEntity<?> aquariumMain() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<AquariumResponse.AquariumDTO> responseDTOList = aquariumService.aquariumMain(sessionUser.getId());

        System.out.println(responseDTOList);
        return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    }



    // 어항 수정
    @PutMapping("/aquariums/{aquariumId}")
    public ResponseEntity<?> update(@RequestBody @Valid AquariumRequest.AquariumDTO requestDTO, Errors errors, @PathVariable int aquariumId) {
        
        System.out.println("requestDTO : "+requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        AquariumResponse.AquariumDTO responseDTO = aquariumService.update(requestDTO, sessionUser, aquariumId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



    // 어항 생성
    @PostMapping("/aquariums")
    public ResponseEntity<?> create(@RequestBody @Valid AquariumRequest.AquariumDTO requestDTO, Errors errors) {
        
        System.out.println("requestDTO : "+requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        AquariumResponse.AquariumDTO responseDTO = aquariumService.create(requestDTO, sessionUser);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



    // 다이어리 생성
    @PostMapping("/aquariums/{aquariumId}/diary")
    public ResponseEntity<?> diaryCreate(@RequestBody @Valid AquariumRequest.DiaryDTO requestDTO, Errors errors, @PathVariable int aquariumId) {
        
        System.out.println("requestDTO : "+requestDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        AquariumResponse.DiaryDTO responseDTO = aquariumService.diaryCreate(requestDTO, sessionUser, aquariumId);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }


}
