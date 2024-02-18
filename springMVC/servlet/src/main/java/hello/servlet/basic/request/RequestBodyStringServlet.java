package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /**
         *  Make Study. 16강 API 메세지 바디 (단순 텍스트)
         *
         * */

        /// Postman으로 Test
        // - urlPatterns 입력 후, 1. Body - raw 2. body 내용 적고 3. send
        ServletInputStream inputStream = request.getInputStream(); // body 내용을 바이트 코드로
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바이트를 String으로 - 인코딩 정보는 반드시 알려주어야 한다.

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("OK 2");


    }
}
