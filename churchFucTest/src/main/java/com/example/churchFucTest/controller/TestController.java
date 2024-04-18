package com.example.churchFucTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    // 메인
    @GetMapping("/main")
    public String main()
    {
        return "main";
    }


    /// 교회소개 ///
    @GetMapping("/intro")
    public String intro()
    {
        return "intro";
    }

    // 교회 역사
    @GetMapping("/ch_history")
    public String ch_history()
    {
        return "ch_history";
    }

    // 담임목사님 인사말
    @GetMapping("/ch_pastor")
    public String ch_pastor()
    {
        return "ch_pastor";
    }



    // 교회 소식
    @GetMapping("/news")
    public String news()
    {
        return "news";
    }

    // 설교
    @GetMapping("/sermons")
    public String sermons()
    {
        return "sermons";
    }

    // 다음 세대
    @GetMapping("/newGeneration")
    public String newGeneration()
    {
        return "newGeneration";
    }


    @GetMapping("/test")
    public String test()
    {
        return "test";
    }


}
