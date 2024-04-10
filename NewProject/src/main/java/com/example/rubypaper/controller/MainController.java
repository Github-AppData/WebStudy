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

    /// Diretory of Produce Controller Start
    @GetMapping("/churchProduce")
    public String Produce(){
        return "produce/churchProduce";
    }

    @GetMapping("/produce/pastor")
    public String ProducePastor(){
        return "produce/pastor";
    }

    @GetMapping("/produce/history")
    public String ProduceHistory(){
        return "produce/history";
    }

    @GetMapping("/produce/goverment")
    public String ProduceGoverment(){
        return "produce/goverment";
    }

    @GetMapping("/produce/worship")
    public String ProduceWorship(){
        return "produce/worship";
    }

    @GetMapping("/produce/map")
    public String ProduceMap(){
        return "produce/map";
    }



    /// Diretory of Produce Controller End

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

    @GetMapping("/portfolioOverview")
    public String portfolioOverview(){

        return "portfolioOverview";
    }

    @GetMapping("/portfolioItem")
    public String portfolioItem(){

        return "portfolioItem";
    }




}
