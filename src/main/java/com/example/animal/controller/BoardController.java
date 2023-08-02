package com.example.animal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public void registerGET(){

    }
}
