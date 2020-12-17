package com.yu.handle;

import com.yu.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 16:06
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 全局统一异常返回信息
     */
    public static final String DEFAULT_ERROR_MESSAGE = "系统维护，请稍后访问";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleAllError(HttpServletRequest request,Exception e){
        log.error("系统内部异常", e);
        return BaseResponse.fail();
    }
}
