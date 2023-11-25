package com.example.kakao.aquarium;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

public interface AquariumRepository extends JpaRepository<Aquarium, Integer> {

    List<Aquarium> findByUserId(int userId);

    
    // Lazy인데 EAGER처럼 fishList 처음부터 가져오게
    @EntityGraph(attributePaths = "fishList")
    @Query("SELECT a FROM Aquarium a WHERE a.user.id = :userId")
    List<Aquarium> findByUserIdEagerFishList(@Param("userId") int userId);
    
}
