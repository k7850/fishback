package com.example.kakao._repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kakao._entity.Equipment;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

}
