package com.example.animal.repository;

import com.example.animal.domain.AnimalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalInfoRepository extends JpaRepository<AnimalInfo, String> {
    Page<AnimalInfo> findByCareAddrContaining(String keyword, Pageable pageable);

    Page<AnimalInfo> findByKindCdContaining(String keyword, Pageable pageable);
}