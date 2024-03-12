package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Make Study. 51강 HTTP 요청 파라미터 - 쿼리 파라미터, HTML Form
 * Make Study. 52강 HTTP 요청 파라미터 - @RequestParam
 * Make Study. 53강 HTTP 요청 파라미터 - @ModelAttribute
 */
@RestController
@Slf4j
public class RequestParamController {


    // Test : Chrome
    // ?username=kim&age=1
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }


    @RequestMapping("/request-param-v2")
    public String requestParamV(
            @RequestParam("username") String username,
            @RequestParam("age") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username={}, age={}", username, age); //
        return "ok";
    }

    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-v5")
    public String requestParamV5(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {

        // required에 값을 false 할 때, null이 들어갈 수 있는 자료형으로 해야 한다.
        // int는 null이 들어갈 수가 없다. 그래서, Integer를 넣는다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @RequestMapping("/request-param-multimap")
    public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttrilbuteV1(@ModelAttribute HelloData hd)
    {
        log.info("username={}, age={}", hd.getUsername(), hd.getAge());
        log.info("helloData={}", hd);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttrilbuteV2(HelloData hd)
    {
        log.info("username={}, age={}", hd.getUsername(), hd.getAge());
        log.info("helloData={}", hd);
        return "ok";
    }

}
