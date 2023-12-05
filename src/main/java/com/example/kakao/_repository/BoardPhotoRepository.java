package com.example.kakao._repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakao._entity.BoardPhoto;

import java.util.List;

public interface BoardPhotoRepository extends JpaRepository<BoardPhoto, Integer> {

}
