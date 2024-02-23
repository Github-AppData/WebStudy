package hello.servlet.web.frontcontoller.v2;

import hello.servlet.web.frontcontoller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    /**
     * Make Study. 30강. View 분리 (v2)
     * */


    // CV1 하고 다른 점 : MyView를 반환한다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
