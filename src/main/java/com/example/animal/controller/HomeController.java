package com.example.animal.controller;

import com.example.animal.dto.AnimalInfo;
import com.example.animal.service.ImageService;
import com.example.animal.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class HomeController {
    private final TestService testService;
    private final ImageService imageService;

    @GetMapping("/")
    public String index(Model model) throws IOException {
        List<String> filenames = testService.getRandomFiveEntities();
        List<String> encodedImages = new ArrayList<>();
        for(int i = 0; i <= 2; i++){
            String popfile = filenames.get(i);
            try {
                byte[] imageData = imageService.getImageDataFromUrl(popfile);
                String encodedImage = Base64.getEncoder().encodeToString(imageData);
                encodedImages.add(encodedImage);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        model.addAttribute("images", encodedImages);

        return "index";
    }

    @Autowired
    public HomeController(TestService testService, ImageService imageService) {
        this.testService = testService;
        this.imageService = imageService;
    }

}
