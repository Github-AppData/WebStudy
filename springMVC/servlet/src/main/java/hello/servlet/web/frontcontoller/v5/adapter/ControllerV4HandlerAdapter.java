package hello.servlet.web.frontcontoller.v5.adapter;

import hello.servlet.web.frontcontoller.ModelView;
import hello.servlet.web.frontcontoller.UtillsFactory;
import hello.servlet.web.frontcontoller.Utils;
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
        ControllerV4 controller = (ControllerV4) handler; //


        Utils rp = UtillsFactory.createUtill("requestParameter");
        Map<String, String> paramMap = rp.createParamMap(request); // 데이터를 HashMap으로 바꿔서 넣어준다.
        HashMap<String, Object> model = new HashMap<>(); // 모델을 생성하고
        String viewname = controller.process(paramMap, model); // 4. 핸들러 호출


        /// return Type -> ModelView
        // 그래서, viewname 이름을 modelview 객체로 바꿔서 넣는다.
        ModelView mv = new ModelView(viewname);
        mv.setModel(model);

        return mv; // 5. ModelView 반환
    }

}
