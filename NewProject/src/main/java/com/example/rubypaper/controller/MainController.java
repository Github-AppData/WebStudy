package com.example.rubypaper.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j

public class MainController {


    @GetMapping("/home")
    public String blogMain(){

        return "blog-home";
    }

    @GetMapping("/about")
    public String blogAbout(){
        return "about";
    }

    @GetMapping("/contact")
    public String blogContact(){

        return "contact";
    }

    @GetMapping("/pricing")
    public String blogPricing(){

        return "pricing";
    }

    @GetMapping("/faq")
    public String blogFaq(){

        return "faq";
    }


}
