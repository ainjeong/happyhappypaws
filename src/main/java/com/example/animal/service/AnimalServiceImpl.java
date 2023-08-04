package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.repository.AnimalInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AnimalServiceImpl implements AnimalService{
    private final AnimalInfoRepository animalInfoRepository;
    @Override
    public AnimalInfo readOne(String desertionNo) {
        AnimalInfo animalInfo = animalInfoRepository.findById(desertionNo).get();
        return animalInfo;
    }

    @Override
    public PageResponseDTO<AnimalInfo> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("desertionNo");
        return null;
    }
}
