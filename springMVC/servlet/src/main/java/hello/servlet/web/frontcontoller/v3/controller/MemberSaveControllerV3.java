package hello.servlet.web.frontcontoller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v3.ControllerV3;

import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // createParamMap 메서드로 생성한, parameter Data를 저장하고 있는 paramMap을 .get username, age에 저장.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 저장한, username, age를 Member 객체에 넣어준다.
        Member member = new Member(username, age);
        memberRepository.save(member); // repo에 저장.

        ModelView mv = new ModelView("save"); // 논리 뷰 이름 설정
        mv.getModel().put("member", member); // 필요한 모델 객체 넣어서 반환 = 데이터 저장.
        return mv; // 반환
    }
}
