package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Make Study. 23강 서블릿으로 회원 관리 웹 애플리케이션 만들기
         * */

        System.out.println("MemberSaveServlet.service");

        /// 파라미터 값을 꺼내는 코드 - GET, POST 상관없다.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age")); // age는 정수 -> Integer.parseInt 형 변횐

        /// 비즈니스 로직 Start !
        Member member = new Member(username, age); // 등록
        memberRepository.save(member); // 저장

        // 결과를 확인하기 위해서, 데이터 응답을 html로 시각적으로 보이게. (기본설정 + 동적 html)
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");


    }

}
