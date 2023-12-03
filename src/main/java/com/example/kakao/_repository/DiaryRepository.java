package com.example.kakao._repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakao._entity.Diary;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

}
