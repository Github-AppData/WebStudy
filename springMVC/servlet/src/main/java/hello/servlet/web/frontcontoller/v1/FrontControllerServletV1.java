package hello.servlet.web.frontcontoller.v1;

import hello.servlet.web.frontcontoller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontoller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /fornt-controller/v1/* - v1 아래에 있는 어떠한 파일도 이 url이 호출
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    /**
     * Make Study. 29강. 프론트 컨트롤러 도입 (v1)
     * */

    // 매핑 정보를 담아두는 Map
    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();


    /** 매핑 정보 메서드 - URI와 메서드 매핑 (해당 URI가 들어오면, 메서드를 반환한다.)**/
    public FrontControllerServletV1() {

        // key, value
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI(); // 주소 창에 있는 URI를 받아온다.

        // requestURI와 controllerV1Map에 있는 URI가 같으면, 해당 컨트롤러로 이동하는 것이다.
        ControllerV1 controller = controllerV1Map.get(requestURI); // 1. 찾으면, 메서드를 리턴 받는다.

        // 예외처리 - 페이지가 없거나 찾지 못할 때.
        if(controller == null){
            System.out.println("controller == null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not found
            return; // 종료
        }

        // 조회가 잘 된 경우.
        System.out.println("okay");
        controller.process(request, response); // 2. 리턴 받은 메서드의 process로 가서 비즈니스 로직을 실행한다.
    }
}
