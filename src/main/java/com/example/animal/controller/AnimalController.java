package com.example.animal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    @GetMapping("/list")
    public String animalGET(Model model){
        return "animal/list";

    }
}
