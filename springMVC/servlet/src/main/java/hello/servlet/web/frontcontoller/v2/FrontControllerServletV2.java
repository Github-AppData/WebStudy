package hello.servlet.web.frontcontoller.v2;

import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v1.ControllerV1;
import hello.servlet.web.frontcontoller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontoller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /fornt-controller/v1/* - v1 아래에 있는 어떠한 파일도 이 url이 호출
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    /**
     * Make Study. 29강. 프론트 컨트롤러 도입 (v2)
     * */

    // 매핑 정보를 담아두는 Map
    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();


    /** 매핑 정보 메서드 - URI와 메서드 매핑 (해당 URI가 들어오면, 메서드를 반환한다.)**/
    public FrontControllerServletV2() {
        // key, value
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI(); // 주소 창에 있는 URI를 받아온다.

        // requestURI와 controllerV1Map에 있는 URI가 같으면, 해당 컨트롤러로 이동하는 것이다.
        ControllerV2 controller = controllerV2Map.get(requestURI); // 1. 찾으면, 메서드를 리턴 받는다.

        // 예외처리 - 페이지가 없거나 찾지 못할 때.
        if(controller == null){
            System.out.println("controller == null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not found
            return; // 종료
        }

        // 조회가 잘 된 경우.
        MyView view = controller.process(request, response);// 2. 리턴 받은 메서드의 process로 가서 비즈니스 로직을 실행한다.
        view.render(request, response);
    }
}
