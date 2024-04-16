package com.example.churchFucTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/main")
    public String main()
    {
        return "main";
    }

    @GetMapping("/intro")
    public String intro()
    {
        return "intro";
    }

    @GetMapping("/news")
    public String news()
    {
        return "news";
    }

    @GetMapping("/sermons")
    public String sermons()
    {
        return "sermons";
    }

    @GetMapping("/newGeneration")
    public String newGeneration()
    {
        return "newGeneration";
    }
}
