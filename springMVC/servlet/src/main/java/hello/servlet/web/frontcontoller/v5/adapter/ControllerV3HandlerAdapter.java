package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.v3.ControllerV3;
import hello.servlet.web.frontcontoller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Make Study. 33강. 유연한 컨트롤러 v5
 * */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    /**
     *
     * @param handler
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // 넘어 온 핸들러가 컨트롤러v3 야 ?
    }


    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */

    // supports을 거쳐서 cv3가 맞는지 넘어오기 때문에, 캐스팅해도 괜찮다.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

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
