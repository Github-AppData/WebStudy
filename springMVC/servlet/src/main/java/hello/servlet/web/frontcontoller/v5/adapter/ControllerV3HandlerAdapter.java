package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.UtillsFactory;
import hello.servlet.web.frontcontoller.Utils;
import hello.servlet.web.frontcontoller.v3.ControllerV3;
import hello.servlet.web.frontcontoller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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

        Utils rp = UtillsFactory.createUtill("requestParameter");

        Map<String, String> paramMap = rp.createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }
}
