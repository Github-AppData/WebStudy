package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Make Study. 55강 응답 - 정적 리소스, 뷰 템플릿
 */
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mv = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!");

        return "response/hello";
    }



    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){ // 권장하지 않는 방식이다.
        model.addAttribute("data", "hello!");
    }
}
