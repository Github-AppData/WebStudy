package hello.servlet.web.frontcontoller;

import hello.servlet.web.frontcontoller.Impl.RequestParameterUtills;

/** 여러 Utill들을 모아놓은 곳이다. **/
public class UtillsFactory {

    /**
     * @param utillType
     * @return
     */
    public static Utils createUtill(String utillType){
        // 요청 파라미터와 관련된 모델
        if("requestParameter".equals(utillType)) {
            return new RequestParameterUtills();
        }
        throw new IllegalArgumentException("Unknown Utill Type: "+ utillType);
    }

}
