package hello.servlet.web.frontcontoller.v1.controller;

import hello.servlet.web.frontcontoller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 구현 컨트롤러
public class MemberFormControllerV1 implements ControllerV1 {

    /**
     * Make Study. 29강. 프론트 컨트롤러 도입 (v1)
     * */

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("MemberFormControllerV1.process");

        // jsp로 보낸다.
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);


    }
}
