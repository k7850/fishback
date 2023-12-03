package com.example.kakao.aquarium;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception403;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao._core.errors.exception.Exception500;
import com.example.kakao._core.utils.ImageUtils;
import com.example.kakao._core.utils.JwtTokenUtils;
import com.example.kakao._entity.Diary;
import com.example.kakao._entity.Equipment;
import com.example.kakao._entity.enums.UserTypeEnum;
import com.example.kakao._repository.DiaryRepository;
import com.example.kakao._repository.EquipmentRepository;
import com.example.kakao.aquarium.Aquarium;
import com.example.kakao.aquarium.AquariumRepository;
import com.example.kakao.board.Board;
import com.example.kakao.board.BoardRepository;
import com.example.kakao.book.Book;
import com.example.kakao.comment.Comment;
import com.example.kakao.fish.Fish;
import com.example.kakao.fish.FishRequest;
import com.example.kakao.user.User;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;

import lombok.RequiredArgsConstructor;

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
public class AquariumService {
    private final AquariumRepository aquariumRepository;
    private final EquipmentRepository equipmentRepository;
    private final DiaryRepository diaryRepository;





    // 메인페이지
    public List<AquariumResponse.AquariumDTO> aquariumMain(int sessiunUserId) {
        
        List<Aquarium> aquariumList = aquariumRepository.findByUserIdEagerFishList(sessiunUserId);

        List<AquariumResponse.AquariumDTO> responseDTOList = aquariumList.stream()
                .map(aquarium -> new AquariumResponse.AquariumDTO(aquarium))
                .collect(Collectors.toList());

        return responseDTOList;
    }




    // 어항 수정
    @Transactional
    public AquariumResponse.AquariumDTO update(AquariumRequest.AquariumDTO requestDTO, User sessionUser, int aquariumId) {

        Aquarium aquarium = aquariumRepository.findById(aquariumId)
                .orElseThrow(() -> new Exception404(aquariumId + "없음"));

        if(aquarium.getUser().getId() != sessionUser.getId()){
            throw new Exception403(aquariumId + "권한없음");
        }

        List<Integer> IdList = requestDTO.getEquipmentDTOList().stream()
                .filter(equipmentDTO -> equipmentDTO.getId() >= 1)
                .map(equipmentDTO -> equipmentDTO.getId())
                .collect(Collectors.toList());

        List<Equipment> deleteEquipmentList = aquarium.getEquipmentList().stream()
                .filter(equipment -> !(IdList.contains(equipment.getId())) )
                .collect(Collectors.toList());

        equipmentRepository.deleteAll(deleteEquipmentList);
                
        requestDTO.getEquipmentDTOList().stream()
                .filter(equipmentDTO -> equipmentDTO.getId() < 1)
                .forEach(equipmentDTO -> equipmentDTO.setId(0));

        List<Equipment> equipmentList = requestDTO.getEquipmentDTOList().stream()
                .map(equipmentDTO -> equipmentDTO.toEntity())
                .collect(Collectors.toList());

        equipmentRepository.saveAll(equipmentList);

        aquarium.setEquipmentList(equipmentList);

        aquarium.setPhoto(requestDTO.getPhoto());
        aquarium.setTitle(requestDTO.getTitle());
        aquarium.setIntro(requestDTO.getIntro());
        aquarium.setIsFreshWater(requestDTO.getIsFreshWater());
        aquarium.setSize(requestDTO.getSize());

        if( requestDTO.getBase64Image() != null && !(requestDTO.getBase64Image().isEmpty()) ){
            byte[] decodedBytes = Base64.decodeBase64(requestDTO.getBase64Image());
            aquarium.setPhoto("aquarium/" + ImageUtils.updateImageBase64(decodedBytes, "aquarium/", sessionUser.getEmail()));
        }
        
        aquariumRepository.save(aquarium);
        
        AquariumResponse.AquariumDTO responseDTO = new AquariumResponse.AquariumDTO(aquarium);
        return responseDTO;
    }




    // 어항 생성
    @Transactional
    public AquariumResponse.AquariumDTO create(AquariumRequest.AquariumDTO requestDTO, User sessionUser) {

        Aquarium aquarium = Aquarium.builder()
                .user(sessionUser)
                .title(requestDTO.getTitle())
                .intro(requestDTO.getIntro())
                .isFreshWater(requestDTO.getIsFreshWater())
                .size(requestDTO.getSize())
                .build();

        if( requestDTO.getBase64Image() != null && !(requestDTO.getBase64Image().isEmpty()) ){
            byte[] decodedBytes = Base64.decodeBase64(requestDTO.getBase64Image());
            aquarium.setPhoto("aquarium/" + ImageUtils.updateImageBase64(decodedBytes, "aquarium/", sessionUser.getEmail()));
        }

        aquariumRepository.save(aquarium);


        requestDTO.getEquipmentDTOList().stream()
                .forEach(equipmentDTO -> equipmentDTO.setId(0));

        requestDTO.getEquipmentDTOList().stream()
                .forEach(equipmentDTO -> equipmentDTO.setAquariumId(aquarium.getId()));

        List<Equipment> equipmentList = requestDTO.getEquipmentDTOList().stream()
                .map(equipmentDTO -> equipmentDTO.toEntity())
                .collect(Collectors.toList());

        equipmentRepository.saveAll(equipmentList);

        aquarium.setEquipmentList(equipmentList);

        // aquarium.setBoardList(new ArrayList<>());
        aquarium.setDiaryList(new ArrayList<>());
        aquarium.setFishList(new ArrayList<>());
        aquarium.setScheduleList(new ArrayList<>());

        AquariumResponse.AquariumDTO responseDTO = new AquariumResponse.AquariumDTO(aquarium);
        return responseDTO;
    }




    // 다이어리 생성
    @Transactional
    public AquariumResponse.DiaryDTO diaryCreate(AquariumRequest.DiaryDTO requestDTO, User sessionUser, int aquariumId) {

        Aquarium aquarium = aquariumRepository.findById(aquariumId)
                .orElseThrow(() -> new Exception404(aquariumId + "없음"));

        if(aquarium.getUser().getId() != sessionUser.getId()){
            throw new Exception403(aquariumId + "권한없음");
        }


        Diary diary = Diary.builder()
                .aquarium(aquarium)
                .title(requestDTO.getTitle())
                .text(requestDTO.getText())
                .build();

        if( requestDTO.getBase64Image() != null && !(requestDTO.getBase64Image().isEmpty()) ){
            byte[] decodedBytes = Base64.decodeBase64(requestDTO.getBase64Image());
            diary.setPhoto("diary/" + ImageUtils.updateImageBase64(decodedBytes, "diary/", sessionUser.getEmail()));
        }

        diaryRepository.save(diary);

        AquariumResponse.DiaryDTO responseDTO = new AquariumResponse.DiaryDTO(diary);
        return responseDTO;
    }






}