package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello") // urlPatterns은 url 매핑, name 서블릿 이름
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service"); // 단축키 soutm - 현재
        System.out.println("request = " + request); // soutv - 단축키
        System.out.println("response = " + response); // soutv - 단축키

        // 요청
        String username = request.getParameter("username");
        System.out.println("username = " + username); // 쿼리 파라미터 조회 가능하다.

        // 요청에 대한 응답
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
