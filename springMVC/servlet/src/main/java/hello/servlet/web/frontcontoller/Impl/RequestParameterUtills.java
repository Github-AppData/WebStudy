package hello.servlet.web.frontcontoller.Impl;

import hello.servlet.web.frontcontoller.Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestParameterUtills implements Utils {

    @Override
    public Map<String, String> createParamMap(HttpServletRequest request) {
        // paramMap : 파라미터 키와 값을 저장하는 맵
        Map<String, String> paramMap = new HashMap<>();

        // .forEachRemaining을 통해서 모든 파라미터 이름(paramName)을 다 가져와서 저장한 다음에,
        // request.getParameter(paramName)을 이용해서 데이터를 꺼냅니다.
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap; // 저장된 키와 값을 반환합니다.
    }
}
