package com.example.kakao._repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakao._entity.BoardEmoticon;

import java.util.List;
import java.util.Optional;

public interface BoardEmoticonRepository extends JpaRepository<BoardEmoticon, Integer> {

    BoardEmoticon findByUserIdAndBoardId(int userId, int boardId);
}
