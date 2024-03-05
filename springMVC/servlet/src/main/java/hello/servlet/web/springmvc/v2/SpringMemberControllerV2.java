package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Make Study. 43강 스프링 시작하기 - 컨트롤러 통합 ()
 */
@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm(){
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

        // createParamMap 메서드로 생성한, parameter Data를 저장하고 있는 paramMap을 .get username, age에 저장.
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 저장한, username, age를 Member 객체에 넣어준다.
        Member member = new Member(username, age);
        memberRepository.save(member); // repo에 저장.

        ModelAndView mv = new ModelAndView("save"); // 논리 뷰 이름 설정
        mv.addObject("member", member); // 필요한 모델 객체 넣어서 반환 = 데이터 저장.
        return mv; // 반환
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll(); // repo에 저장된 데이터를 싹 긁어 모은 다음에

        ModelAndView mv = new ModelAndView("members"); // 등록
        mv.addObject("members", members);
        return mv;
    }
}
