package hello.servlet.web.frontcontoller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Make Study. 30강. View 분리 (v2)
 * */

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    /** view를 렌더링 하는 메서드 **/
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 8. 데이터가 저장된 request를 jsp에다가 넘긴다. 받은 jsp는 paramMap.get을 이용해서 사용한다.
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // 렌더가 오면, 모델에 있는 값을 다 꺼내서 HttpRequest에다가 setAttribute로 다 넣는다.
        // = model을 forEach 시키면서 돌면서 key, value 즉, 데이터를 request에다가 저장 시킨다.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
