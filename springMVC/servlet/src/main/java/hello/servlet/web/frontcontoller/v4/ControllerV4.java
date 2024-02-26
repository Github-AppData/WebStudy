package hello.servlet.web.frontcontoller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * Make Study. 32강. 실용적인 컨트롤러 v4
     * */

    /**
     *
     * @param paramMap
     * @param model
     * @return viewname
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
