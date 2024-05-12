package com.example.churchFucTest.controller;

import com.example.churchFucTest.config.LoginUser;
import com.example.churchFucTest.domain.SessionUser;
import com.example.churchFucTest.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final PostsService postsService;

    @PostMapping("/login")
    public String loginPost(Model model, @LoginUser SessionUser user){

        // TODO : 로그인 관련 객체, Service, Repository, DTO 만들기
        //         그런 다음에 세션 처리하기 (관리자와 일반유저 나뉘어서)

        if(user != null){ // Session이 있으면,
            model.addAttribute("userName", user.getUsername()); // 전달해 준다.
        }
        return "login";
    }

    @GetMapping("/login")
    public String loginGet(Model model){

        return "login";
    }

    // 메인
    @GetMapping("/main")
    public String main()
    {
        return "/main";
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





    // 설교2


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


//    @GetMapping("/test")
//    public String test()
//    {
//        return "test";
//    }

    @GetMapping("/test2")
    public String test2()
    {
        return "test2";
    }

    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    // @Login SessionUser user - 어노테이션으로 세션관리하기
//    @GetMapping("/test")
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        Page<PostsDTO> postsPages = postsService.paging(pageable);
//
//        /**
//         * blockLimit : page 개수 설정
//         * 현재 사용자가 선택한 페이지 앞 뒤로 blockLimit 갯수만큼의 페이지씩만 보여준다.
//         * ex : 현재 사용자가 설정한 blockLimit가 4페이지라면 2, 3, (4), 5, 6
//         */
//        int blockLimit = 2;
//        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());
//
//        model.addAttribute("postsPages", postsPages);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "test";
//    }




}
