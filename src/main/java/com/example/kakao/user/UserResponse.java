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




    @Getter
    @Setter
    @ToString
    public static class MainPageDTO {
        private int userId;
        private List<MainAquariumDTO> mainAquariumDTOList;
        private List<MainBoardDTO> mainBoardDTOList;


        public MainPageDTO(int userId, List<Aquarium> aquariumList, List<Board> boardList) {
            this.userId = userId;

            this.mainAquariumDTOList = aquariumList.stream()
                    .map(t -> new MainAquariumDTO(t))
                    .collect(Collectors.toList());

            this.mainBoardDTOList = boardList.stream()
                    .map(t -> new MainBoardDTO(t))
                    .collect(Collectors.toList());
        }


        @Getter
        @Setter
        @ToString
        class MainAquariumDTO {
            private int id;
            private String title;
            private String intro;
            private String photo;

            MainAquariumDTO(Aquarium aquarium) {
                this.id = aquarium.getId();
                this.title = aquarium.getTitle();
                this.intro = aquarium.getIntro();
                this.photo = aquarium.getPhoto();
            }
        }

        @Getter
        @Setter
        @ToString
        class MainBoardDTO {
            private int id;
            private int userId;
            private String username;
            private String title;
            private String text;
            private List<String> photoList;
            private String video;
            private Timestamp createdAt;
            private Timestamp updatedAt;

            MainBoardDTO(Board board) {
                this.id = board.getId();
                this.userId = board.getUser().getId();
                this.username = board.getUser().getUsername();
                this.title = board.getTitle();
                this.text = board.getText();

                this.photoList = board.getBoardPhotoList().stream()
                        .map(t -> t.getPhotoURL())
                        .collect(Collectors.toList());

                this.video = board.getVideo();
                this.createdAt = board.getCreatedAt();
                this.updatedAt = board.getUpdatedAt();
            }
        }





    }





}
