package com.example.kakao.fish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import javax.transaction.Transactional;

public interface FishRepository extends JpaRepository<Fish, Integer> {
    
    

}
