package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Member;
import com.example.animal.dto.LikeRequestDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.service.AnimalService;
import com.example.animal.service.LikeService;
import com.example.animal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnimalController {
    private final LikeService likeService;
    private final MemberService memberService;
    private final AnimalService animalService;
    @GetMapping("/animal/list")
    public String animalGET(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<AnimalInfo> responseDTO = animalService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "animal/list";

    }

    @GetMapping("/animal/read")
    public String animalOneGET(String desertionNo, Model model, Authentication authentication){
        if (authentication == null){
            AnimalInfo animalInfo = animalService.readOne(desertionNo);
            model.addAttribute("animal", animalInfo);
            model.addAttribute("isLiked", null);
            return "animal/read";
        }else{
            String id = authentication.getName();
            Member member = memberService.findById(id);
            AnimalInfo animalInfo = animalService.readOne(desertionNo);
            boolean isLiked = likeService.isLikeExists(member, animalInfo);
            model.addAttribute("isLiked", isLiked);
            model.addAttribute("animal", animalInfo);
            return "animal/read";
        }
        }



    }



