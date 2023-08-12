package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Member;
import com.example.animal.dto.LikeRequestDTO;
import com.example.animal.service.AnimalService;
import com.example.animal.service.LikeService;
import com.example.animal.service.MemberService;
import com.example.animal.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class LikeController {
    private final LikeService likeService;
    private final MemberService memberService;
    private final AnimalService animalService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/animal/like")
    public String AnimalLike(@RequestParam("desertionNo") String desertionNo, Model model) throws Exception {
        model.addAttribute("desertionNo", desertionNo);
        LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
        likeRequestDTO.setAnimalInfoDesertionNo(desertionNo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String id = authentication.getName();
            Member member = memberService.findById(id);
            String memberId = member.getId();
            likeRequestDTO.setMemberId(memberId);
        }
        likeService.insert(likeRequestDTO);
        return "redirect:/animal/read?desertionNo=" + desertionNo;
    }




    @PostMapping("/animal/unlike")
    public String delete(@RequestParam("desertionNo") String desertionNo, Authentication authentication, Model model){
        model.addAttribute("desertion", desertionNo);
        LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
        likeRequestDTO.setAnimalInfoDesertionNo(desertionNo);
        String id = authentication.getName();
        Member member = memberService.findById(id);
        likeRequestDTO.setMemberId(member.getId());
        likeService.delete(likeRequestDTO);
        return "redirect:/animal/read?desertionNo=" + desertionNo;
    }
}
