package hello.servlet.web.frontcontoller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Make Study. 30강. View 분리 (v2)
 * */
public class MemberSaveControllerV2 implements ControllerV2 {


    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보낸다.
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save.jsp");
    }
}
