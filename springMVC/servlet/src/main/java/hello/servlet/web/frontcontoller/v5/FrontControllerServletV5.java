package hello.servlet.web.frontcontoller.v5;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.MyView;
import hello.servlet.web.frontcontoller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontoller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontoller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontoller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontoller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontoller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontoller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Make Study. 33강. 유연한 컨트롤러 v5
 *
 * Use Stydy. 33강. 유연한 컨트롤러1 v5
 *            34강. 유연한 컨트롤러2 v5
 * */
@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {


    // private Map<String, ControllerV4> controllerV4Map = new HashMap<>(); // 기존 - cv4로 정해져 있는 컨트롤러

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); // URItoCV매핑 정보
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 어댑터 리스트

    // 생성자 - 이 클래스가 실행되면 자동으로 실행
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    /**
     * Adapters Save -> 'handlerAdapters'
     */
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    /** handler TO URI Mapping Method */ // <- 2. 핸들러 어댑터 목록
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 핸들러 조회 & 2.
        Object handler = getHandler(request); // URI TO Handler 매핑 정보를 통해, 해당되는 handler를 가지고 온다.

        // 만약, 가지고 온 handler가 없다면,
        if(handler == null)
        {
            // 상태 코드를 SC_NOT_FOUND 즉, 404 Error를 내라.
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler); // 3. 핸들러 어댑터 실행

        String viewname = mv.getViewname();
        MyView view = viewResolver(viewname); // 6. 뷰 리졸버 실행

        view.render(mv.getModel(), request,response); // 7. view 반환
    }

    private static MyView viewResolver(String viewname) {
        return new MyView("/WEB-INF/views/" + viewname + ".jsp");
    }

    /**
     * 핸들러를 처리할 수 있는 어댑터 조회
     * */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter a;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)){ // adapter가 지원하는 handler 인지.
                return adapter;
            }
        }
        // 지원을 하지 않으면 던진다 -> IllegalArgumentException
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    /**
     * 1. 핸들러 매핑 (=조회)
     * */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI); // handlerMappingMap에 저장된 URI TO Handler 매핑 정보를 인수로 넣어서 유무확인
    }
}
