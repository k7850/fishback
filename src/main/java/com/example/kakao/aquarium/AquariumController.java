package com.example.kakao.aquarium;

import com.example.kakao._core.utils.ApiUtils;
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
public class AquariumController {

    private final AquariumService aquariumService;
    private final HttpSession session;




    // 메인페이지
    @GetMapping("/aquariums")
    public ResponseEntity<?> aquariumMain() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<AquariumResponse.AquariumDTO> responseDTOList = aquariumService.aquariumMain(sessionUser.getId());

        return ResponseEntity.ok().body(ApiUtils.success(responseDTOList));
    }



}
