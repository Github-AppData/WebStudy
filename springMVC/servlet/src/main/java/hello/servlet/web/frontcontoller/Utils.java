package hello.servlet.web.frontcontoller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * RequestParameter에 필요한 Utils 모음
 */
public interface Utils {

    /**
     * @param request
     * @return
     * @description: parameter에 들어있는 키(=이름)로 꺼낸 다음에 HashMap(=paramMap)으로 바꾸는 메서드
     * @type: requestParameter
     **/
    default Map<String, String> createParamMap(HttpServletRequest request){
        return null;
    };

    default Map<String, String> createParamMap2(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
