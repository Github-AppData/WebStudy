package hello.servlet.web.frontcontoller.v4.controller;

import hello.servlet.web.frontcontoller.v4.ControllerV4;

import java.util.Map;

/**
 * Make Study. 32강. 실용적인 컨트롤러 v4
 * */
public class MemberFormControllerV4 implements ControllerV4 {

    // 모델 뷰가 아예 없이 뷰 이름만 반환하면 된다.
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
