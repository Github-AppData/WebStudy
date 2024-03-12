package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * Make Study. 54강. HTTP 요청 메세지 - JSON
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // Convert

        log.info("messageBody={}", messageBody); // log
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // json -> object
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge()); // object.getXxxx

        response.getWriter().write("ok"); // 응답 메세지
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException
    {
        log.info("messageBody={}", messageBody); // log
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); // json -> object
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge()); // object.getXxxx - 굳이 바꾸어주어야 할까 ?

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData)
    {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge()); // object.getXxxx - 굳이 바꾸어주어야 할까 ?
        return "ok";
    }
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity)
    {
        HelloData data = httpEntity.getBody();
        log.info("username={}, age={}", data.getUsername(), data.getAge()); // object.getXxxx - 굳이 바꾸어주어야 할까 ?

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data)
    {
        // JSON(들어올 때) -> Object -> JSON (나갈 때)
        log.info("username={}, age={}", data.getUsername(), data.getAge()); // object.getXxxx - 굳이 바꾸어주어야 할까 ?
        return data; // HTTP 메세지 응답에 객체가 박혀서 나가게 된다. => JSON 형태 그대로 출력
    }




}
