package hello.servlet.web.frontcontoller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontoller.v4.ControllerV4;

import java.util.Map;

/**
 * Make Study. 32강. 실용적인 컨트롤러 v4
 * */
public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) { // 전달받은 model를

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // Member 객체에 저장.
        Member member = new Member(username, age);
        memberRepository.save(member); // repo save

        model.put("member", member); // 전달받은 model 에다가 데이터 객체 넣어주기만 하면 끝. -> model save
        return "save"; // 뷰의 논리이름 반환.
    }
}
