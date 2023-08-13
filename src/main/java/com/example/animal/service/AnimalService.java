package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.dto.BoardDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.repository.AnimalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimalService {
    AnimalInfo readOne(String desertionNo);

    PageResponseDTO<AnimalInfo> list(PageRequestDTO pageRequestDTO);

    Page<AnimalInfo> findByCareAddrContaining(String keyword, Pageable pageable);

    Page<AnimalInfo> findByKindCdContaining(String keyword, Pageable pageable);
}
