package com.example.animal.repository;

import com.example.animal.domain.AnimalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalInfoRepository extends JpaRepository<AnimalInfo, String> {
}
