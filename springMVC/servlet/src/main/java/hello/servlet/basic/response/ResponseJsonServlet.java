package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * Make Study. 20강 HTTP 응답 데이터 (API JSON) - equl. REST API JSON
         * */

        // Test : urlPatterns

        // 응답은 크게 세 가지
        // 1. 단순 텍스트 2. HTML 3. JSON

        // HTTP 응답으로 JSON을 반환할 때는 ContentType을 'application/json'로 지정해야 한다.
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(21);

        // hellodata 객체를 {"username": "kim", "age": 21} 스타일로 변횐하기 위한 'objectMapper.writeValueAsString'
        String result = objectMapper.writeValueAsString(helloData); // object -> json
        response.getWriter().write(result);


    }
}
