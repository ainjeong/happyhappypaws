package com.example.animal.repository;

import com.example.animal.dto.AnimalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalInfoRepository extends JpaRepository<AnimalInfo, String> {
}
