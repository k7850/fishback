package com.example.kakao.board;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.kakao._entity.BoardEmoticon;
import com.example.kakao._entity.Equipment;
import com.example.kakao._entity.enums.EmoticonEnum;
import com.example.kakao._entity.enums.FishClassEnum;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.book.Book;
import com.example.kakao.comment.Comment;
import com.example.kakao.fish.Fish;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class BoardResponse {



    @Getter
    @Setter
    @ToString
    public static class BoardMainDTO {
        private int id;
        private int userId;
        private String username;
        private String title;
        // private String text;
        private List<String> photoList;
        private String video;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        // private AquariumDTO aquariumDTO;
        // private FishDTO fishDTO;
        // private List<CommentDTO> commentDTOList;
        // private EmoticonCount emoticonCount;
        private Integer viewCount;
        private Integer commentCount;
        private Boolean isView;
        private Boolean isAquarium;


        BoardMainDTO(Board board, int sessionUserId) {
            this.id = board.getId();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.title = board.getTitle();

            this.photoList = board.getBoardPhotoList().stream()
                    .map(t -> t.getPhoto())
                    .collect(Collectors.toList());

            this.video = board.getVideo();

            this.viewCount = board.getViewCount();
            
            this.createdAt = board.getCreatedAt();
            this.updatedAt = board.getUpdatedAt();

            this.commentCount = board.getCommentList().size();

            this.isView = board.getBoardEmoticonList().stream()
                    .anyMatch(t -> t.getUser().getId() == sessionUserId);

            if(board.getAquarium() != null){
                this.isAquarium = true;
            } else if(board.getFish() != null){
                this.isAquarium = false;
            }
        }
    }







    @Getter
    @Setter
    @ToString
    public static class BoardDTO {
        private int id;
        private int userId;
        private String username;
        private String title;
        private String text;
        private String video;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private List<String> photoList;
        private AquariumDTO aquariumDTO;
        private FishDTO fishDTO;
        private Integer viewCount;
        private List<CommentDTO> commentDTOList;
        private EmoticonCount emoticonCount;


        BoardDTO(Board board, int sessionUserId) {
            this.id = board.getId();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.title = board.getTitle();
            this.text = board.getText();

            this.photoList = board.getBoardPhotoList().stream()
                    .map(t -> t.getPhoto())
                    .collect(Collectors.toList());

            this.video = board.getVideo();

            this.viewCount = board.getViewCount();
            
            this.createdAt = board.getCreatedAt();
            this.updatedAt = board.getUpdatedAt();

            if(board.getAquarium() != null) this.aquariumDTO = new AquariumDTO(board.getAquarium());
            if(board.getFish() != null) this.fishDTO = new FishDTO(board.getFish());

            this.commentDTOList = board.getCommentList().stream()
                    .map(comment -> new CommentDTO(comment, sessionUserId))
                    .collect(Collectors.toList());

            this.emoticonCount = new EmoticonCount(board.getBoardEmoticonList(), sessionUserId);
            
        }
    }





    @Getter
    @Setter
    @ToString
    public static class EmoticonCount {
        private EmoticonEnum myEmoticonEnum; // TODO
        private Integer countTHUMB = 0;
        private Integer countHEART = 0;
        private Integer countCONGRATATU = 0;
        private Integer countSMILE = 0;
        private Integer countCRY = 0;
        private Integer countANGRY = 0;

        public EmoticonCount(List<BoardEmoticon> boardEmoticonList, int sessionUserId) {

            myEmoticonEnum = boardEmoticonList.stream()
                    .filter(t -> t.getUser().getId() == sessionUserId)
                    .findFirst()
                    .map(t -> t.getEmoticonEnum())
                    .orElse(null);
            
            boardEmoticonList.stream()
                    .forEach(t -> {
                        if(t.getEmoticonEnum() != null){
                            if(t.getEmoticonEnum()==EmoticonEnum.THUMB) countTHUMB++;
                            if(t.getEmoticonEnum()==EmoticonEnum.HEART) countHEART++;
                            if(t.getEmoticonEnum()==EmoticonEnum.CONGRATATU) countCONGRATATU++;
                            if(t.getEmoticonEnum()==EmoticonEnum.SMILE) countSMILE++;
                            if(t.getEmoticonEnum()==EmoticonEnum.CRY) countCRY++;
                            if(t.getEmoticonEnum()==EmoticonEnum.ANGRY) countANGRY++;
                        }
                    });
            
            // Map<EmoticonEnum, Long> emoticonCountMap = boardEmoticonList.stream()
            //         .collect(Collectors.groupingBy(t -> t.getEmoticonEnum(), Collectors.counting()));
            // countTHUMB = emoticonCountMap.getOrDefault(EmoticonEnum.THUMB, 0L).intValue();
            // countHEART = emoticonCountMap.getOrDefault(EmoticonEnum.HEART, 0L).intValue();
            // countCONGRATATU = emoticonCountMap.getOrDefault(EmoticonEnum.CONGRATATU, 0L).intValue();
            // countSMILE = emoticonCountMap.getOrDefault(EmoticonEnum.SMILE, 0L).intValue();
            // countCRY = emoticonCountMap.getOrDefault(EmoticonEnum.CRY, 0L).intValue();

        }
    }






    @Getter
    @Setter
    @ToString
    public static class AquariumDTO {
        private int id;
        private String title;
        private Boolean isFreshWater;
        private String size; // 길이/폭/높이
        private List<FishDTO> fishDTOList;
        private List<EquipmentDTO> equipmentDTOList;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private List scheduleDTOList = new ArrayList();
        private List diaryDTOList = new ArrayList();

        public AquariumDTO(Aquarium aquarium) {
            this.id = aquarium.getId();
            this.title = aquarium.getTitle();
            this.isFreshWater = aquarium.getIsFreshWater();
            this.size = aquarium.getSize();
            this.fishDTOList = aquarium.getFishList().stream()
                    .map(fish -> new FishDTO(fish))
                    .collect(Collectors.toList());
            this.equipmentDTOList = aquarium.getEquipmentList().stream()
                    .map(equipment -> new EquipmentDTO(equipment))
                    .collect(Collectors.toList());
            this.createdAt = aquarium.getCreatedAt();
            this.updatedAt = aquarium.getUpdatedAt();
        }
    }





    
    @Getter
    @Setter
    @ToString
    public static class FishDTO {
        private int id;
        private int aquariumId;
        private Book book;
        private FishClassEnum fishClassEnum;
        private String name;
        private Integer quantity;
        private Boolean isMale;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public FishDTO(Fish fish) {
            this.id = fish.getId();
            this.aquariumId = fish.getAquarium().getId();
            this.book = fish.getBook();
            this.fishClassEnum = fish.getFishClassEnum();
            this.name = fish.getName();
            this.quantity = fish.getQuantity();
            this.isMale = fish.getIsMale();
            this.createdAt = fish.getCreatedAt();
            this.updatedAt = fish.getUpdatedAt();
        }
    }


    

    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class EquipmentDTO {
        private int id;
        private int aquariumId;
        private String category;
        private String name;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        public EquipmentDTO(Equipment equipment) {
            this.id = equipment.getId();
            this.aquariumId = equipment.getAquarium().getId();
            this.category = equipment.getCategory();
            this.name = equipment.getName();
            this.createdAt = equipment.getCreatedAt();
            this.updatedAt = equipment.getUpdatedAt();
        }
    }



    

    
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class CommentDTO {
        private int id;
        private int boardId;
        private int userId;
        private String username;
        private String text;
        private Boolean isDelete;
        private Timestamp createdAt;
        private Timestamp updatedAt;

        private Integer likeCommentCount;
        private Integer dislikeCommentCount;
        private Boolean isMyLike = false;
        private Boolean isMyDislike = false;


        public CommentDTO(Comment comment, int sessionUserId) {
            this.id = comment.getId();
            this.boardId = comment.getBoard().getId();
            this.userId = comment.getUser().getId();
            this.username = comment.getUser().getUsername();
            this.text = comment.getText();
            this.isDelete = comment.getIsDelete();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();

            this.likeCommentCount = comment.getLikeCommentList().stream()
                    .map(t -> (t.getIsLike() == true) ? 1 : 0)
                    .reduce(0, (a, b) -> a + b);

            this.dislikeCommentCount = comment.getLikeCommentList().stream()
                    .map(t -> (t.getIsLike() == false) ? 1 : 0)
                    .reduce(0, (a, b) -> a + b);

            comment.getLikeCommentList().stream()
                    .filter(likeComment -> likeComment.getUser().getId() == sessionUserId)
                    .findFirst()
                    .ifPresent(likeComment -> {
                        if (likeComment.getIsLike() == true) {
                            this.isMyLike = true;
                        } else {
                            this.isMyDislike = true;
                        }
                    });
        }
    }




}
