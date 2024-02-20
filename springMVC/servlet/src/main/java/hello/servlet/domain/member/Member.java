package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    /**
     * Make Study. 22강 회원 관리 웹 애플리케이션 요구사항
     *
     * using Study. 23강 서블릿으로 회원 관리 웹 애플리케이션 만들기
     * */

    private Long id;
    private String username;
    private int age;

    public Member() {
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
