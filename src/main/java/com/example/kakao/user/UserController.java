package com.example.kakao.user;

import com.example.kakao._core.utils.ApiUtils;
import com.example.kakao._core.utils.ImageUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;










@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final HttpSession session;





    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid UserRequest.JoinDTO requestDTO, Errors errors) {

        UserResponse.LoginResponseDTO responseDTO = userService.join(requestDTO);

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }
    

    // 자동로그인
    @PostMapping("/autologin")
    public ResponseEntity<?> autoLogin() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        UserResponse.LoginResponseDTO responseDTO = userService.autologin(sessionUser);

        return ResponseEntity.ok().header("Authorization", "Bearer " + responseDTO.getJwt()).body(ApiUtils.success(responseDTO));
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserRequest.LoginDTO requestDTO, Errors errors) {

        UserResponse.LoginResponseDTO responseDTO = userService.login(requestDTO);

        return ResponseEntity.ok().header("Authorization", "Bearer " + responseDTO.getJwt()).body(ApiUtils.success(responseDTO));
    }







}
