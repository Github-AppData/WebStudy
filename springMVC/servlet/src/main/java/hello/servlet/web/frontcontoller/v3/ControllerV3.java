package hello.servlet.web.frontcontoller.v3;


import hello.servlet.web.frontcontoller.ModelView;

import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
public interface ControllerV3 {
    // 서블릿 기술을 사용하지 않기 위해서, Map을 사용한다.
    ModelView process(Map<String, String> paramMap);
}
