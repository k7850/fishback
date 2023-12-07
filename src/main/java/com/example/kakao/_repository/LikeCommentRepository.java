package com.example.kakao._repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakao._entity.LikeComment;

import java.util.List;
import java.util.Optional;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Integer> {

    Optional<LikeComment> findByUserIdAndCommentId(int userId, int commentId);

}
