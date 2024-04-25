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
    @GetMapping("/ch_intro")
    public String ch_intro()
    {
        return "ch_intro";
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

    @GetMapping("/ch_servePeople")
    public String ch_servePeople()
    {
        return "ch_servePeople";
    }




    // 교회 소식
    @GetMapping("/ch_news")
    public String news()
    {
        return "ch_news";
    }

    // 설교
    @GetMapping("/ch_sermons")
    public String sermons()
    {
        return "ch_sermons";
    }

    // 다음 세대
    @GetMapping("/ch_newGeneration")
    public String newGeneration()
    {
        return "ch_newGeneration";
    }

    // 오시는 길
    @GetMapping("/ch_Contact")
    public String ch_Contact()
    {
        return "ch_Contact";
    }

    // 오시는 길
    @GetMapping("/ch_way")
    public String ch_way()
    {
        return "ch_way";
    }


    @GetMapping("/test")
    public String test()
    {
        return "test";
    }

    @GetMapping("/test2")
    public String test2()
    {
        return "test2";
    }




}
