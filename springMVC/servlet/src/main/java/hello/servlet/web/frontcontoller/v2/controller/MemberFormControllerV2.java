package hello.servlet.web.frontcontoller.v2.controller;

import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Make Study. 30강. View 분리 (v2)
 * */
public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");

    }
}
