package hello.servlet.web.frontcontoller.v3;

import hello.servlet.web.frontcontoller.*;
import hello.servlet.web.frontcontoller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Make Study. 31강. Model 추가 (v3)
 * */
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {



    // 매핑 정보를 담아두는 Map
    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();


    /** 매핑 정보 메서드 - URI와 메서드 매핑 (해당 URI가 들어오면, 컨트롤러를 반환한다.)**/
    public FrontControllerServletV3() {
        // key, value
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI(); // 주소 창에 있는 URI를 받아온다.

        // requestURI와 controllerV3Map에 있는 URI가 같으면, 해당 컨트롤러로 이동하는 것이다.
        ControllerV3 controller = controllerV3Map.get(requestURI); // 매핑이 되면, 컨트롤러 메서드를 리턴 받는다.
                    // controller = MemberFormControllerV3

        // 예외처리 - URI에 맞는 컨트롤러를 찾지 못할 때
        if(controller == null){
            notMappingURItoCV(response);
            return; // 종료
        }

        // TODO : 이 페이지 view 로직 완전 다 이해하기. (베어 -> 프론트 컨트롤러 v3 로직 설명 => 22:10 ~)
        //   데이터가 어떻게 넘어가서 보여 지는지 이해하기. - line 59 ~ (render -> 'modelToRequestAttribute')

        /// view 관한 설정
        Utils rp = UtillsFactory.createUtill("requestParameter");

        Map<String, String> paramMap = rp.createParamMap(request); // parameter Data -> HashMap Convert To Store
        ModelView mv = controller.process(paramMap); // 1. 해당 컨트롤러 메서드로 간 다음에, 뷰의 이름을 구해서 mv에 저장.

        String viewname = mv.getViewname(); // 2. 구한 view 이름을 viewname에 저장한다.
        MyView view = viewResolver(viewname); // 3. 그래서, 물리 경로 + 논리경로 + .jsp 을 한다.

        // 4. 해당 view를 RequestDispatcher을 이용해서 렌더링 한다. -> 데이터가 담겨져 있는 model도 같이 넘긴다.
        view.render(mv.getModel(), request, response);

        // 문제가 생겼을 때 쓰는 단축키 : option + Enter
    }

    /**
     * @param response
     * @throws IOException
     * @description: notMappingURItoController
     */
    private static void notMappingURItoCV(HttpServletResponse response) throws IOException {
        System.out.println("controller == null");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not found Print
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("404 Not Found - 페이지를 찾을 수 없습니다"); // 띄운다.
    }


    // 디테일 한 로직은 메서드를 뽑는 것이 좋다. (feat. 레벨을 맞추는 의미에서...)
    // 디테일 한 로직 메서드로 뽑는 단축키 : option + command + M
    private static MyView viewResolver(String viewname) {
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }

    // 디테일 한 로직은 메서드를 뽑는 것이 좋다. (feat. 레벨을 맞추는 의미에서...)
    // 디테일 한 로직 메서드로 뽑는 단축키 : option + command + M

    /**
     * @param request
     * @return
     * @description: parameter에 들어있는 키(=이름)로 꺼낸 다음에 HashMap(=paramMap)으로 바꾸는 메서드
     */
    private static Map<String, String> createParamMap(HttpServletRequest request) {

        // paramMap : 파라미터 키와 값을 저장 하는 맵
        Map<String, String> paramMap = new HashMap<>(); // paramMap : 파라미터 저장 하는 맵

        // .forEachRemaining을 통해서 모든 파라미터 이름(paramName)을 다 가지고 와서 저장한 다음에,
        // request.getParameter(paramName)을 이용해서 데이터를 꺼낸다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        // request.getParameter(paramName) : paramName 이름을 기반해서 데이터를 꺼낸다.

        return paramMap; // 저장된 키와 값을 반환한다.
    }
}
