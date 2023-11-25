package com.example.kakao.aquarium;

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
public class AquariumService {
    private final AquariumRepository aquariumRepository;





    // 메인페이지
    public List<AquariumResponse.AquariumDTO> aquariumMain(int sessiunUserId) {
        
        List<Aquarium> aquariumList = aquariumRepository.findByUserIdEagerFishList(sessiunUserId);

        List<AquariumResponse.AquariumDTO> responseDTOList = aquariumList.stream()
                .map(aquarium -> new AquariumResponse.AquariumDTO(aquarium))
                .collect(Collectors.toList());

        return responseDTOList;
    }


}