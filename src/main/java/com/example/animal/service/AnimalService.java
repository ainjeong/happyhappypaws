package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.dto.BoardDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.repository.AnimalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AnimalService {
    AnimalInfo readOne(String desertionNo);

    PageResponseDTO<AnimalInfo> list(PageRequestDTO pageRequestDTO);
}
