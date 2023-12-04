package com.example.kakao.user;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.comment.Comment;

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
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final AquariumRepository aquariumRepository;




    // 회원가입
    @Transactional
    public UserResponse.LoginResponseDTO join(UserRequest.JoinDTO requestDTO) {
        try {
            User user = requestDTO.toEntity();
            user.setUserTypeEnum(UserTypeEnum.NORMAL); // 일반 가입창으로 가입하면 무조건 노말유저

            userRepository.save(user);



            String jwt = JwtTokenUtils.create(user);
            UserResponse.LoginResponseDTO responseDTO = new UserResponse.LoginResponseDTO(user);
            responseDTO.setJwt(jwt);

            return responseDTO;

        } catch (Exception e) {
            throw new Exception500("오류");
        }
    }


    // 자동로그인
    public UserResponse.LoginResponseDTO autologin(User sessionUser) {
        User userPS = userRepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception400("자동로그인 오류"));

        String jwt = JwtTokenUtils.create(userPS);

        UserResponse.LoginResponseDTO responseDTO = new UserResponse.LoginResponseDTO(userPS);
        responseDTO.setJwt(jwt);

        return responseDTO;
    }


    // 로그인
    public UserResponse.LoginResponseDTO login(UserRequest.LoginDTO requestDTO) {
        User userPS = userRepository.findByEmailAndPassword(requestDTO.getEmail(), requestDTO.getPassword())
                .orElseThrow(() -> new Exception400("email이나 password가 틀림 : " + requestDTO.getEmail()));

        String jwt = JwtTokenUtils.create(userPS);

        UserResponse.LoginResponseDTO responseDTO = new UserResponse.LoginResponseDTO(userPS);
        responseDTO.setJwt(jwt);

        return responseDTO;
    }





}