package hello.servlet.web.frontcontoller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll(); // repo에 저장된 데이터를 싹 긁어 모은 다음에
        ModelView mv = new ModelView("members"); // 등록
        mv.getModel().put("members", members); //
        return mv;
    }
}
