package hello.servlet.web.frontcontoller;

import hello.servlet.web.frontcontoller.Impl.RequestParameterUtills;

public class UtillsFactory {

    /**
     *
     * @param utillType
     * @return
     */
    public static Utils createUtill(String utillType){
        //
        if("requestParameter".equals(utillType)) {
            return new RequestParameterUtills();
        }
        throw new IllegalArgumentException("Unknown Utill Type: "+ utillType);
    }
}
