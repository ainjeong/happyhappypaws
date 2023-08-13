package com.example.animal.repository.search;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalSearch {

    Page<AnimalInfo> search1(Pageable pageable);


    Page<AnimalInfo> searchAll(String[] types, String keyword, Pageable pageable);
}
