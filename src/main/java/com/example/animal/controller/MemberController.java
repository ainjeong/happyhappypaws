package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Like;
import com.example.animal.domain.Member;
import com.example.animal.dto.MemberJoinDTO;
import com.example.animal.service.ImageService;
import com.example.animal.service.LikeService;
import com.example.animal.service.MemberService;
import com.nimbusds.jose.util.Pair;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LikeService likeService;
    private final ImageService imageService;

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get................");
        log.info("logout: " + logout);

        if(logout != null){
            log.info("user logout.......");
        }
    }

    @GetMapping("/join")
    public void joinGET(){
        log.info("get join...");
    }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes, Model model){
        try{
            memberService.join(memberJoinDTO);
        }catch (MemberService.MidExistException e){
          redirectAttributes.addFlashAttribute("error", "id");
          //  model.addAttribute("error", "id");
            return "redirect:/member/join?error=id";
        }
        redirectAttributes.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }

    @GetMapping("/info")
    public String infoGET(Authentication auth, Model model){
        String id = auth.getName();
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
        List<Like> likes = likeService.findByMemberId(member.getId());
        List<AnimalInfo> infos = new ArrayList<>();
        for (Like like : likes){
            infos.add(like.getAnimalInfo());
        }
        List<String> filenames = infos.stream()
                .map(AnimalInfo::getPopfile)
                .collect(Collectors.toList());
        List<String> nos = infos.stream()
                .map(AnimalInfo::getDesertionNo)
                .collect(Collectors.toList());
        List<String> encodedImages = new ArrayList<>();
        List<String> desirtionNos = new ArrayList<>();

        for(int i = 0; i<= likes.size() -1; i++){
            String popfile = filenames.get(i);
            String no = nos.get(i);
            try {
                byte[] imageData = imageService.getImageDataFromUrl(popfile);
                String encodedImage = Base64.getEncoder().encodeToString(imageData);
                encodedImages.add(encodedImage);

                desirtionNos.add(no);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        List<Pair<String, String>> combinedList = new ArrayList<>();

        for (int i = 0; i < encodedImages.size(); i++) {
            Pair<String, String> pair = Pair.of(encodedImages.get(i), desirtionNos.get(i));
            combinedList.add(pair);
        }

        model.addAttribute("combinedList", combinedList);

        return "member/info";
    }
}
