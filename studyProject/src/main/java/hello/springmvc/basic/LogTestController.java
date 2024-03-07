package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Make Study. 46강 로깅 간단히 알아보기
 */


@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name); // 어느 때나 무조건 다 출력이 되기 때문에, 쓰면 안 된다.

        log.info(" info log="+ name); // 이렇게 쓰면 안된다. -> 자바 언어의 특징 => 쓰지도 않는데 연산이 일어난다. (너무 낭비다.)

        log.trace("trace log={}", name); // 로컬에서 개발할 때.
        log.debug("debug log={}", name); // 디버그 할 떄, - 개발용
        log.info(" info log={}", name); // 중요한 정보 - 운영 할 때.
        log.warn(" warn log={}", name); // 경고
        log.error("error log={}", name); // 에러

        return "ok";
    }


}
