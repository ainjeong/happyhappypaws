package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Board;
import com.example.animal.dto.AnimalInfoDTO;
import com.example.animal.dto.BoardDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.repository.AnimalInfoRepository;
import com.example.animal.repository.search.AnimalSearch;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AnimalServiceImpl implements AnimalService{
    private final AnimalInfoRepository animalInfoRepository;
    private final AnimalSearch animalSearch;
    private final ModelMapper modelMapper;
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
        Page<AnimalInfo> result = animalSearch.searchAll(types, keyword, pageable);
        List<AnimalInfo> dtoList = result.getContent().stream().map(animalInfo -> modelMapper.map(animalInfo, AnimalInfo.class)).collect(Collectors.toList());
        return PageResponseDTO.<AnimalInfo>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public Page<AnimalInfo> findByCareAddrContaining(String keyword, Pageable pageable) {
        return animalInfoRepository.findByCareAddrContaining(keyword, pageable);
    }

    @Override
    public Page<AnimalInfo> findByKindCdContaining(String keyword, Pageable pageable) {
        return animalInfoRepository.findByKindCdContaining(keyword, pageable);
    }


}
