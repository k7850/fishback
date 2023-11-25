package com.example.kakao.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {


}
