package hello.servlet.web.frontcontoller.v3.controller;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v3.ControllerV3;

import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form"); // 물리적인 위치가 아닌 논리적인 이름 사용.

    }
}
