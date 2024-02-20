package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 * */

public class MemberRepository {

    /**
     * Make Study. 22강 회원 관리 웹 애플리케이션 요구사항
     *
     * using Study. 23강 서블릿으로 회원 관리 웹 애플리케이션 만들기
     * */

    private static Map<Long, Member> store = new HashMap<>();
    private static long seqence = 0L; // id 시퀀스

    // 싱글톤 스타일
    // private으로 생성자를 막아야 한다.
    private static final MemberRepository instance = new MemberRepository();


    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){

    }

    // 회원 정보 저장하는 메서드
    public Member save(Member member){
        member.setId(++seqence); // 회원 아이디 자동 증가
        store.put(member.getId(), member); // store에 put으로 회원 아이디를 인덱스로 쓰고 값을 저장한다.

        return member;
    }


    // 회원 아이디를 찾는 메서드
    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 저장소에 있는 모든 데이터를 꺼내서 ArrayList에 넣어서 반환 - 저장소에 있는 자체를 보호하고 싶어서
    }

    public void clearStore() {
        store.clear();
    }




}
