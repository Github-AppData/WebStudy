package com.example.churchFucTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test1")
    public String test1()
    {
        return "index";
    }

    @GetMapping("/test2")
    public String test2()
    {
        return "left-sidebar";
    }

    @GetMapping("/test3")
    public String test3()
    {
        return "no-sidebar";
    }

    @GetMapping("/test4")
    public String test4()
    {
        return "right-sidebar";
    }
}
