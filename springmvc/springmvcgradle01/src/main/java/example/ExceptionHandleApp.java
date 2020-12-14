package example;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yu.wenhua
 * @desc全局异常处理
 * 异常处理中优先局部异常管理，局部异常管理无法处理时候，会使用全局异常管理
 * @date 2020/11/25 15:26
 */
@ControllerAdvice
public class ExceptionHandleApp {
    @ExceptionHandler(value = {ArithmeticException.class})
    public ModelAndView handleException(Exception e){
        e.printStackTrace();
        System.out.println("handleException.................");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg", "全局异常管理" + e.getLocalizedMessage());
        return mv;
    }
}
