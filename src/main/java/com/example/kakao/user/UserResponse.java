package com.example.kakao.user;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.board.Board;
import com.example.kakao.fish.Fish;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class UserResponse {



    @Getter
    @Setter
    @ToString
    public static class LoginResponseDTO {
        private int id;
        private String email;
        private String username;
        private String jwt;
        // private Boolean isAdmin;
        // private Boolean isAuthor;
        private UserTypeEnum userTypeEnum;


        public LoginResponseDTO(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.userTypeEnum = user.getUserTypeEnum();
            // this.isAdmin = user.getIsAdmin();
            // this.isAuthor = user.getIsAuthor();
        }
    }






}
