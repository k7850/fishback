package com.example.kakao.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // @Query("SELECT b FROM Board b WHERE b.title LIKE :keyword OR b.text LIKE :keyword ORDER BY b.id DESC")
    // Page<Board> find2(@Param("keyword") String keyword, Pageable pageable);

    Page<Board> findByTitleContainingOrTextContaining(String keyword, String keyword2, Pageable pageable);

    // Page<Board> findByTitleContaining(String keyword, Pageable pageable);

}
