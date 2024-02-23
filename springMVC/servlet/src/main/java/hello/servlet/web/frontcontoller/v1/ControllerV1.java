package hello.servlet.web.frontcontoller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    /**
     * Make Study. 29강. 프론트 컨트롤러 도입 (v1)
     * */

    // 서블릿이랑 같은 모양을 만들어 둔다.
    // 프론트 컨트롤러는 이 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가져갈 수 있다. - 인터페이스는 매핑정보를 담고 있다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
