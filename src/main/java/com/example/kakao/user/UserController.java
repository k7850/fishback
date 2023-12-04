package com.example.kakao.user;

import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;










import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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






    // @PostMapping("/uploadVideo")
    // public ResponseEntity<String> handleVideoUpload(@RequestParam("file") MultipartFile file) {
    //     if (file.isEmpty()) {
    //         return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
    //     }
    //     try {
    //         // Create the upload directory if it doesn't exist
    //         Files.createDirectories(Paths.get("./images/"));

    //         // Get the file bytes and save it to the server
    //         byte[] bytes = file.getBytes();
    //         Path filePath = Paths.get("./images/" + file.getOriginalFilename());
    //         Files.write(filePath, bytes);

    //         return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>("Failed to upload the file", HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }


}
