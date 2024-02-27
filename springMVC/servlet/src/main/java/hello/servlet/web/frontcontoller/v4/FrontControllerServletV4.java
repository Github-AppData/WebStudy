package hello.servlet.web.frontcontoller.v4;

import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.UtillsFactory;
import hello.servlet.web.frontcontoller.Utils;
import hello.servlet.web.frontcontoller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontoller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontoller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Make Study. 32강. 실용적인 컨트롤러 v4
 * */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {



    // 매핑 정보를 담아두는 Map
    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();


    /** 매핑 정보 메서드 - URI와 메서드 매핑 (해당 URI가 들어오면, 메서드를 반환한다.)**/
    public FrontControllerServletV4() {
        // key, value
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");


        String requestURI = request.getRequestURI(); // 주소 창에 있는 URI를 받아온다.

        // requestURI와 controllerV4Map에 있는 URI가 같으면, 해당 컨트롤러로 이동하는 것이다.
        ControllerV4 controller = controllerV4Map.get(requestURI); // 매핑이 되면, 컨트롤러를 리턴 받는다.

        // 예외처리 - 페이지가 없거나 찾지 못할 때.
        if(controller == null){
            System.out.println("controller == null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not found
            return; // 종료
        }

        Utils rp = UtillsFactory.createUtill("requestParameter"); // 팩토리 메서드를 통해 객체 생성
        Map<String, String> paramMap = rp.createParamMap(request); // 클라이언트는 구체적인 서비스 클래스에 의존하지 않음

        Map<String, Object> model  = new HashMap<>(); /// model을 FrontController에서 직접 만든다. -> ModelView 종속 제거
        String viewname = controller.process(paramMap, model); // 논리이름 반환.

        MyView view = viewResolver(viewname); // 4. 그래서, 물리 경로 + 논리경로 + .jsp 을 한다.

        // 4. 해당 view를 RequestDispatcher을 이용해서 렌더링 한다.
        view.render(model ,request, response); // 기존에 ModelView에서 제공하는 모델을 꺼내지 X

        // 문제가 생겼을 때 쓰는 단축키 : option + Enter
    }

    private static MyView viewResolver(String viewname) {
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }
}
