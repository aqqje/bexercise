package cn.exception.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
public class CustomExceptionResovler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        CustomException customException = null;

        // 如果抛出的是系统自定义异常则直接转换
        if(e instanceof CustomException){
            customException = (CustomException)e;
        }else{
            // 如果抛出的不是系统自定义异常则重新构造一个系统错误异常。
            customException = new CustomException("系统错误，请与系统管理 员联系！");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", customException.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
