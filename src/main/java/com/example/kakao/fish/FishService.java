package com.example.kakao.fish;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.aquarium.AquariumResponse;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.book.Book;
import com.example.kakao.book.BookRepository;
import com.example.kakao.comment.Comment;
import com.example.kakao.schedule.ScheduleRequest;
import com.example.kakao.user.User;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FishService {
    private final FishRepository fishRepository;
    private final AquariumRepository aquariumRepository;
    private final BookRepository bookRepository;




    // 생물 추가
    @Transactional
    public AquariumResponse.FishDTO create(FishRequest.FishDTO requestDTO, int sessionUserId, int aquariumId, MultipartFile photoFile) {

        Aquarium aquarium = aquariumRepository.findById(aquariumId)
                .orElseThrow(() -> new Exception404(aquariumId + "없음"));

        if(aquarium.getUser().getId() != sessionUserId){
            throw new Exception403(aquariumId + "권한없음");
        }

        Book book = null;
        if(requestDTO.getBookId() != 0){
            book = bookRepository.findById(requestDTO.getBookId())
            .orElseThrow(() -> new Exception404(requestDTO.getBookId() + "없음"));
        }


        Fish fish = requestDTO.toEntity(aquarium, book);

        if( !(photoFile == null || photoFile.getSize() == 0) ){
            fish.setPhoto("fish/" + ImageUtils.updateImage(photoFile, "fish/"));
        }

        fishRepository.save(fish);

        AquariumResponse.FishDTO responseDTO = new AquariumResponse.FishDTO(fish);
        return responseDTO;
    }





    
    // 생물 삭제
    @Transactional
    public AquariumResponse.FishDTO delete(int sessionUserId, int fishId) {

        Fish fish = fishRepository.findById(fishId)
                .orElseThrow(() -> new Exception404(fishId + "없음"));

        if(fish.getAquarium().getUser().getId() != sessionUserId){
            throw new Exception403(fishId + "권한없음");
        }
        
        fishRepository.deleteById(fish.getId());

        AquariumResponse.FishDTO responseDTO = new AquariumResponse.FishDTO(fish);
        return responseDTO;
    }






    // 생물 어항 이동
    @Transactional
    public AquariumResponse.FishDTO move(int sessionUserId, int fishId, int aquariumId) {

        Fish fish = fishRepository.findById(fishId)
                .orElseThrow(() -> new Exception404(fishId + "없음"));

        Aquarium aquarium = aquariumRepository.findById(aquariumId)
                .orElseThrow(() -> new Exception404(aquariumId + "없음"));

        if(fish.getAquarium().getUser().getId() != sessionUserId){
            throw new Exception403(fishId + "권한없음");
        }

        if(aquarium.getUser().getId() != sessionUserId){
            throw new Exception403(aquariumId + "권한없음");
        }

        fish.setAquarium(aquarium);

        AquariumResponse.FishDTO responseDTO = new AquariumResponse.FishDTO(fish);
        return responseDTO;
    }





    // 생물 수정
    @Transactional
    public AquariumResponse.FishDTO update(FishRequest.FishDTO requestDTO, int sessionUserId, int fishId, int aquariumId, MultipartFile photoFile) {

        Fish oldFish = fishRepository.findById(fishId)
                .orElseThrow(() -> new Exception404(fishId + "없음"));

        Aquarium aquarium = aquariumRepository.findById(aquariumId)
                .orElseThrow(() -> new Exception404(aquariumId + "없음"));

        Book book = null;
        if(requestDTO.getBookId() != 0){
            book = bookRepository.findById(requestDTO.getBookId())
            .orElseThrow(() -> new Exception404(requestDTO.getBookId() + "없음"));
        }

        if(oldFish.getAquarium().getUser().getId() != sessionUserId){
            throw new Exception403(fishId + "권한없음");
        }

        if(aquarium.getUser().getId() != sessionUserId){
            throw new Exception403(aquariumId + "권한없음");
        }
        
        Fish fish = requestDTO.toEntity(aquarium, book);
        
        fish.setId(fishId);
        fish.setCreatedAt(oldFish.getCreatedAt());
        fish.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        
        if( !(photoFile == null || photoFile.getSize() == 0) ){
            fish.setPhoto("fish/" + ImageUtils.updateImage(photoFile, "fish/"));
        }
        
        fishRepository.save(fish);
        
        AquariumResponse.FishDTO responseDTO = new AquariumResponse.FishDTO(fish);
        return responseDTO;
    }






}