package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;



// 단축키 : option + Enter -> Add On-demand....
import static org.assertj.core.api.Assertions.*; // 자동으로 들어가기 때문에, Assertions 안 붙여도 된다.

class MemberRepositoryTest { // 전체 테스트

    /**
     * Make Study. 22강 회원 관리 웹 애플리케이션 요구사항
     *
     * 주의 점.
     * : 1. 싱글톤으로 작성 했기 때문에, new X / 2. Test할 대상을 가지고 와야 한다. -> MemberRepository
     */

    MemberRepository memberRepository = MemberRepository.getInstance();


    @AfterEach
    void afterEach() { // 이거 없으면 순서 보장이 안 되서, 문제가 생긴다.
        memberRepository.clearStore(); // AfterEach : Test 끝난 후에, Test 초기화
    }

    @Test
    void save() { // 저장 테스트
        /// given - 문제 (feat. 이런 것이 주어졌을 떄)
        Member member = new Member("hello", 20);

        /// when - 행위 (feat. 이런 걸 실행해야 해)
        Member savedMember = memberRepository.save(member); // 저장

        /// then - 결과 확인 (feat. 결과가 이거여야 해 !)
        // Assertions - 테스트에서 특정 조건이 충족 되는지 확인하는 데 사용.
        Member findMember = memberRepository.findById(savedMember.getId()); // 저장한 멤버의 아이디를 가지고 와서.
        assertThat(findMember).isEqualTo(savedMember); // 특정 조건 여부 Assertions, 같은 지 확인하는 isEqualTo
    }

    @Test
    void findAll() { // 전체 조회 테스트
        /// given - 문제 (feat. 이런 것이 주어졌을 떄)ㄴ
        Member member1 = new Member("member1", 23);
        Member member2 = new Member("member2", 35);

        memberRepository.save(member1);
        memberRepository.save(member2);

        /// when - 행위 (feat. 이런 걸 실행해야 해)
        List<Member> result = memberRepository.findAll();


        /// then - 결과 확인 (feat. 결과가 이거여야 해 !)
        // Assertions - 테스트에서 특정 조건이 충족 되는지 확인하는 데 사용.
        assertThat(result.size()).isEqualTo(2); // size : result의 사이즈 확인
        assertThat(result).contains(member1, member2); // contains : 포함 되어져 있느냐
    }

}
