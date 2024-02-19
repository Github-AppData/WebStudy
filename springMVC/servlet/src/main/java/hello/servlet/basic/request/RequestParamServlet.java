package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *  Make Study. 14강 GET 쿼리 파라미터
         *
         *  using Study. 15강 POST HTML Form - Parameter Test
         *  using Study. 16강 API 메세지 바디 (단순 텍스트) -  API Body message Text Test
         * */

        // Test : Postman
        // Way : urlPatterns 입력 후, 1. Body - x-www-form-urlencoded 2. body에 키와 값 데이터 적고 3. send

        /// 전체 파라미터 조회 -> .asIterator()...
        System.out.println();
        System.out.println("[전체 파라미터 조회] - Start");

        // paramNames은 키
        // request.getParameter(paramName)는 값
        request.getParameterNames().asIterator()
                .forEachRemaining(paramNames -> System.out.println(paramNames +"=" + request.getParameter(paramNames)));
       
        System.out.println("[전체 파라미터 조회] - End");


        /// 단일 파라미터 조회 -> request.getParameter
        // (GET 쿼리 파라미터, POST HTML Form 둘 다 파라미터 키와 값을 꺼내올 수 있다.)
        System.out.println();
        System.out.println("[단일 파라미터 조회] - Start");

        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] - End");
        System.out.println();


        /// 하나의 URL에 파라미터 키가 중복이 될 경우 -> getParameterValues
        // 키는 하나이고, 값은 여러 개로 들어오기 때문에 키 중복 이라도 전체 파라미터 조회 때는 하나의 키와 값이 출력이 되는 구조이다.
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");

        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        response.getWriter().write("OK");

    }
}
