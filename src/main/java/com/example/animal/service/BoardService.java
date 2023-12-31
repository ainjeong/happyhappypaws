package com.example.animal.service;

import com.example.animal.domain.Board;
import com.example.animal.dto.BoardDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BoardService {
    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

}
