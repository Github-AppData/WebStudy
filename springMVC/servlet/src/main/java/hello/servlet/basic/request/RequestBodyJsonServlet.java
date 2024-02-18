package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="RequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // JSON을 HelloData 객체로 변환 시키는 라이브러리 -> 'jackson의 ObjectMapper'
    // (feat. JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면...)
    // (feat2. json data -> HelloData object)
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /**
         *  Make Study. 17강 API 메세지 바디 (JSON)
         * */

        // Test : Postman
        // Way : urlPatterns 입력 후, 1. Body - raw, json 2. body 내용 json 스타일로 적고 3. send

        /// 결국에는 JSON도 문자이기 때문에, 텍스트와 동일한 방식으로 InputStream을 써도 문제가 없다.
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);


        /// 객체 변환하는 메서드 -> jackson의 'ObjectMapper'
        // readValue : 객체 안에 있는 값들을 전부 읽는 메서드
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());

        // 응답 화면 데이터
        response.getWriter().write("OK 3");
    }
}
