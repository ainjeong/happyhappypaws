package com.example.animal.controller;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.service.ImageService;
import com.example.animal.service.TestService;
import com.nimbusds.jose.util.Pair;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final TestService testService;
    private final ImageService imageService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) throws IOException {
        List<AnimalInfo> randomEntities = testService.getRandomFiveEntities();
        List<String> filenames = randomEntities.stream()
                .map(AnimalInfo::getPopfile)
                .collect(Collectors.toList());
        List<String> nos = randomEntities.stream()
                .map(AnimalInfo::getDesertionNo)
                .collect(Collectors.toList());
        List<String> encodedImages = new ArrayList<>();
        List<String> desirtionNos = new ArrayList<>();


        for(int i = 0; i <= 2; i++){
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



        model.addAttribute("images", encodedImages);
        model.addAttribute("isUserInRoleUser", request.isUserInRole("ROLE_USER"));
        model.addAttribute("nos", desirtionNos);
        return "index";
    }

    @Autowired
    public HomeController(TestService testService, ImageService imageService) {
        this.testService = testService;
        this.imageService = imageService;
    }
    @GetMapping("/list")
    public String animalList(Model model){

        return "list";
    }

}
