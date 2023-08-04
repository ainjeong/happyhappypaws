package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.dto.BoardDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;

public interface AnimalService {
    AnimalInfo readOne(String desertionNo);

    PageResponseDTO<AnimalInfo> list(PageRequestDTO pageRequestDTO);
}
