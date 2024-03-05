package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Make Study. 44강 스프링 시작하기 - 실용적인 방식
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();


    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }


    @PostMapping("/save")
    public String  save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save"; // 반환 view
    }


    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll(); // repo에 저장된 데이터를 싹 긁어 모은 다음에

        model.addAttribute("members", members);
        return "members";
    }
}
