package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v4.ControllerV4;
import hello.servlet.web.frontcontoller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Make Study. 34강. 유연한 컨트롤러2 v5
 * */

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {

        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request); // 데이터를 HashMap으로 바꿔서 넣어준다.
        HashMap<String, Object> model = new HashMap<>(); // 모델을 생성하고
        String viewname = controller.process(paramMap, model);


        /// return Type -> ModelView
        // 그래서, viewname 이름을 modelview 객체로 바꿔서 넣는다.
        ModelView mv = new ModelView(viewname);
        mv.setModel(model);

        return mv;
    }

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
