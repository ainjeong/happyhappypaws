package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping("/list")
    public String animalGET(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<AnimalInfo> responseDTO = animalService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "animal/list";

    }

    @GetMapping("/read")
    public String animalOneGET(String desertionNo, Model model){
        AnimalInfo animalInfo = animalService.readOne(desertionNo);
        model.addAttribute("animal", animalInfo);
        return "animal/read";
    }
}
