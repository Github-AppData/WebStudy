package com.example.churchFucTest.controller;

import com.example.churchFucTest.config.LoginUser;
import com.example.churchFucTest.domain.User;
import com.example.churchFucTest.dto.SessionUserDTO;
import com.example.churchFucTest.dto.UserDTO;
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

import java.security.NoSuchAlgorithmException;
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
                            @RequestParam String userid, @RequestParam String inputPassword, HttpSession session, HttpServletRequest request,
                            RedirectAttributes rttr){

        session = request.getSession();
        // TODO : 로그인 관련 객체, Service, Repository, DTO 만들기
        //         그런 다음에 세션 처리하기 (관리자와 일반유저 나뉘어서)

        // TODO : 확인 절차 - 로그인한 대상이 DB에 저장되어 있는 id와 맞는지.

        log.info("userid={}", userid);
        log.info("pw={}", inputPassword);
        boolean is_exists; // 로그인 쳌

        try {
            sessionUserDTO = loginAndAuthService.processLogin(userid, inputPassword);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // sessionUserDTO에 값이 없지 않으면,,, - 성공
        if (sessionUserDTO != null) {
            if(sessionUserDTO.getUserId().equalsIgnoreCase("admin")){ // 관리자인가.
                successLoginPakage(model, sessionUserDTO, session);
                return "redirect:/admin"; // 관리자페이지로 redirect

            } else { // 관리자가 아닌 일반 유저이면,
                successLoginPakage(model, sessionUserDTO, session);
                return "redirect:/main";
            }
        } else { // 실패
            log.info("False sessionUserDTO={}", sessionUserDTO);
            return "redirect:/login"; // GET으로 다시 리다이렉트
        }
    }

    private static void successLoginPakage(Model model, SessionUserDTO sessionUserDTO, HttpSession session) {
        session.setAttribute("user", sessionUserDTO); // 세션으롤 저장.
        model.addAttribute("userName", sessionUserDTO.getUsername()); // 전달해 준다. html로
    }

    @GetMapping("/admin")
    public String adminMain(){
        return "adminMain";
    }

    @GetMapping("/login")
    public String loginGet(Model model){
        return "login";
    }

    @GetMapping("/login/forgetPW")
    public String GETforgetPW(Model model){
        return "forgetPW";
    }

    @GetMapping("/login/forgetPW2")
    public String GETforgetPW2(Model model){
        return "forgetPW2";
    }

    @GetMapping("/login/pwUpdateLast")
    public String GETpwUpdateLast(Model model){
        return "pwUpdateLast";
    }

    @PostMapping("/login/pwUpdateLast")
    public String POSTpwUpdateLast(RedirectAttributes rttr, Model model, @RequestParam String password, @RequestParam String new_password){

        // TODO : 전에 쓰던 비번이랑 같은 지 확인하는 작업이 필요하다.

        if(password.equals(new_password)){
            // TODO : 비밀번호 교체작업
            log.info("비밀번호가 맞습니다.");
            return "redirect:/login";
        } else {
            log.info("비밀번호가 틀립니다.");
            return "redirect:/login/pwUpdateLast";
        }
    }

    @PostMapping("/login/forgetPW")
    public String POSTforgetPW(Model model, @RequestParam String userid, RedirectAttributes rttr){

        // TODO : ID CHECK
        boolean is_Id;

        if(loginAndAuthService.checkUserID(userid)){
            log.info("아이디가 존재합니당");
            return "redirect:/login/forgetPW2"; // 다음 단계로
        } else {
            log.info("아이디가 맞지 않습니다.");
            return "redirect:/login/forgetPW"; // 다시 제자리
        }
    }


    @GetMapping("/complete")
    public String complete(Model model){
        return "complete";
    }

    @GetMapping("/login2")
    public String login2Get(Model model){
        return "login2";
    }


    // 메인
    @GetMapping("/main")
    public String main(@LoginUser SessionUserDTO user) {

        log.info("user={}", user);


        return "main";
    }

    @GetMapping("/signUp")
    public String signup(@LoginUser SessionUserDTO sessionUserDTO) {

        return "signUp";
    }

    @PostMapping("/signUp")
    public String postsignup(RedirectAttributes rttr, UserDTO userDTO) {

        System.out.println("postsignup");
        loginAndAuthService.processsignUp(convertToEntity(userDTO));

        return "redirect:/main";
    }

    private static User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setBirthday(userDTO.getBirthday());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setDetailAddress(userDTO.getDetailAddress());
        user.setUserId(userDTO.getUserId());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRoles(userDTO.getRoles());

        LocalDate localDate = LocalDate.now();
        // LocalDate를 java.sql.Date로 변환
        Date sqlDate = Date.valueOf(localDate);

        user.setLoginTime(sqlDate);

        System.out.println("convertToEntity user.getPassWord() = " + user.getPassword());
        return user;
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
