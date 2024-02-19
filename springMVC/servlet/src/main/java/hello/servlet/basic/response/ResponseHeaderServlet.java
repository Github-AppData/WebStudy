package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Make Study. 18강 HttpServletResponse (기본 사용법)
         * */

        /// Test : urlPatterns

        /// GMT - 영국 그리니치 천문대 기준 시간

        /// response 상태코드
        response.setStatus(HttpServletResponse.SC_OK); // 200 - 상수로 정의 되어 있는 것을 쓰면 의미파악 가능.
        // response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400

        /// [response-headers] => 키와 값으로 셋팅. (하드코딩)
        // response.setHeader("Content-Type","text/plain;charset=utf-8"); // 헤더 설정
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 원천 차단
        response.setHeader("Pragma", "no-cache"); // 캐시 원천 차단
        response.setHeader("my-header","hello"); // 내가 만든 임의 헤더


        //content(response); // [Header 편의 메서드]
        //cookie(response); // [Cookie 편의 메서드]
        redirect(response); // [redirect 편의 메서드]

        /// [message body]
        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요.");
    }

    // [Header 편의 메서드]
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2); // (생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // 한 줄로 위 두 줄 커버가능.
    }
}
