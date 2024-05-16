package com.example.churchFucTest.controller;

import com.example.churchFucTest.config.LoginUser;
import com.example.churchFucTest.domain.User;
import com.example.churchFucTest.dto.SessionUserDTO;
import com.example.churchFucTest.service.LoginAndAuthService;
import com.example.churchFucTest.service.PostsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;


@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final PostsService postsService;
    private final LoginAndAuthService loginAndAuthService;

    @PostMapping("/login")
    public String loginPost(Model model, @LoginUser SessionUserDTO sessionUserDTO,
                            @RequestParam String userid, HttpSession session, HttpServletRequest request,
                            RedirectAttributes rttr){

        session = request.getSession();
        // TODO : 로그인 관련 객체, Service, Repository, DTO 만들기
        //         그런 다음에 세션 처리하기 (관리자와 일반유저 나뉘어서)

        // TODO : 확인 절차 - 로그인한 대상이 DB에 저장되어 있는 id와 맞는지.

        log.info("userid={}", userid);
        boolean is_exists; // 로그인 쳌
        sessionUserDTO = loginAndAuthService.processLogin(userid);

        // sessionUserDTO에 값이 없지 않으면,,, - 성공
        if (sessionUserDTO != null) {
            log.info("Success sessionUserDTO={}", sessionUserDTO);
            session.setAttribute("user", sessionUserDTO);
            model.addAttribute("userName", sessionUserDTO.getUsername()); // 전달해 준다. html로
            return "redirect:/main";
        } else { // 실패
            log.info("False sessionUserDTO={}", sessionUserDTO);
            return "redirect:/login"; // GET으로 다시 리다이렉트
        }
    }

    @GetMapping("/login")
    public String loginGet(Model model){
        return "login";
    }

    // 메인
    @GetMapping("/main")
    public String main(@LoginUser SessionUserDTO user) {

        log.info("user={}", user);


        return "main";
    }

    @GetMapping("/signup")
    public String signup(@LoginUser SessionUserDTO sessionUserDTO) {



        return "signup";
    }

    @PostMapping("/signup")
    public String postsignup(RedirectAttributes rttr, @RequestParam String userid,
                             @RequestParam String password, @RequestParam Date birthday,
                             @RequestParam String phoneNumber, @RequestParam String adress,
                             @RequestParam String datailAdress, @RequestParam String email,
                             @RequestParam String username, @RequestParam String roles) {

        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);

        User user = new User();
        user.setPassword(password);
        user.setBirthday(birthday);
        user.setEmail(email);
        user.setUsername(username);
        user.setAdress(adress);
        user.setDatailAdress(datailAdress);
        user.setUserId(userid);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(roles);
        user.setLoginTime(date);

        loginAndAuthService.processsignUp(user);

        // TODO : 회원가입, 비밀번호 처리 다 됨(조건만 달면 됨)
        //          이제 로그인, 회원가입 css만 수정하자.

        return "redirect:/main";
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
    // @Login SessionUserDTO user - 어노테이션으로 세션관리하기
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
