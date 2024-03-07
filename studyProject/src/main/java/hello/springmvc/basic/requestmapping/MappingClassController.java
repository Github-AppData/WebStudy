package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Make Study. 49강 요청 매핑 - API 예시
 */
@RestController
@RequestMapping("/mapping/users")
@Slf4j // log 관련 어노테이션 - lombok
public class MappingClassController {

    @GetMapping
    public String user() { // 조회
        log.info("조회이다.");
        return "get user";
    }

    @PostMapping
    public String addUser() { // 등록
        return "post user";
    }

    @GetMapping("/{userId}")
    public String finduser(@PathVariable String userId) { // 조회
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) { // 수정
        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) { // 삭제
        return "delete userId=" + userId;
    }


}
