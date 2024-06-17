package com.example.churchFucTest.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/findPwNumberSetServlet")
public class findPwNumberSetServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("findPwNumberSetServlet");

		HttpSession session = request.getSession();
        String number = (String) session.getAttribute("num");
		System.out.println("number : "+ number);

        response.setStatus(HttpServletResponse.SC_OK);

        // Content-Type 설정 (JSON으로 응답)
	    request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
	    response.getWriter().write(number);

	}

}
