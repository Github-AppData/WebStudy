package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * Make Study. 50강 HTTP 요청 - 기본, 헤더 조회
 */
@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, // 요청
                          HttpServletResponse response, // 응답
                          HttpMethod httpMethod, // GET, POST, PATCH, DELETE,,,,
                          Locale locale, // 국가의 언어 설정
                          @RequestHeader MultiValueMap<String, String> headerMap, // header는 전부 다 표시.
                          @RequestHeader("host") String host, // host
                          @CookieValue(value = "myCookie", required = false) String cookie){ // 쿠키

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale); // 로케일이 큰 것만 표시.
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("keyA", "value1");
        map.add("keyA", "value2");

        List<String> values = map.get("keyA");

        log.info("MultiValueMap values={}", values);

        return "ok";
    }
}
