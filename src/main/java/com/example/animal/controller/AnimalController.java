package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Member;
import com.example.animal.dto.LikeRequestDTO;
import com.example.animal.dto.PageRequestDTO;
import com.example.animal.dto.PageResponseDTO;
import com.example.animal.repository.search.AnimalSearch;
import com.example.animal.service.AnimalService;
import com.example.animal.service.LikeService;
import com.example.animal.service.MemberService;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnimalController {
    private final LikeService likeService;
    private final MemberService memberService;
    private final AnimalService animalService;
    private final AnimalSearch animalSearch;
    @GetMapping("/animal/list")
    public String animalGET(PageRequestDTO pageRequestDTO, Model model, String[] types, String keyword, Pageable pageable){
        if(keyword == null) {
            PageResponseDTO<AnimalInfo> responseDTO = animalService.list(pageRequestDTO);
            model.addAttribute("responseDTO", responseDTO);
        }else{
            Page<AnimalInfo> responseDTO = animalSearch.searchAll(types, keyword, pageable);
            model.addAttribute("responseDTO", responseDTO);
        }
        return "animal/list";

    }


    /*@GetMapping("/animal/list")
    public String animallGET(@RequestParam(required = false) String keyword, Pageable pageable, Model model, PageRequestDTO pageRequestDTO){
        Page<AnimalInfo> animalInfoPage = null;
        AnimalInfo animalInfo;
        if(keyword==null) {
            PageResponseDTO<AnimalInfo> responseDTO = animalService.list(pageRequestDTO);
            model.addAttribute("responseDTO", responseDTO);
            return "animal/list";
        }else{
            animalInfoPage = animalService.findByKindCdContaining(keyword, pageable);
            if (animalInfoPage.isEmpty()) {
                model.addAttribute("errorMessage", "검색 결과가 없습니다.");
                return "animal/error"; // 에러 페이지를 만들어 사용하거나 기존 페이지 중에서 처리 가능한 페이지로 리턴합니다.
            }
            model.addAttribute("responseDTO", animalInfoPage);
            return "animal/list2";

        }
        //List<Object> list = new ArrayList<>();
       // Gson gson = new Gson();
        //String pageGson = gson.toJson(animalInfoPage);
        //return pageGson;
    }

*/




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



