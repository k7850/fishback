package com.example.kakao.user;

import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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


    // 메인페이지
    @GetMapping("/users/main")
    public ResponseEntity<?> main() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        UserResponse.MainPageDTO responseDTO = userService.main(sessionUser.getId());

        return ResponseEntity.ok().body(ApiUtils.success(responseDTO));
    }



}
