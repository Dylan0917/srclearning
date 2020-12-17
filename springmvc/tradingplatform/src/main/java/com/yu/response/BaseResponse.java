package com.yu.response;

import com.yu.handle.GlobalExceptionHandler;

/**
 * @author yu.wenhua
 * @desc
 * @date 2020/12/17 15:37
 */
//@Data ： 注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
//@Data
public class BaseResponse {
    private int result;
    private String msg;

    public BaseResponse(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public BaseResponse(int result) {
        this.result = result;
    }

    public static BaseResponse fail(){
        return new BaseResponse(0, GlobalExceptionHandler.DEFAULT_ERROR_MESSAGE);
    }

    public static BaseResponse fail(String msg) {
        return new BaseResponse(0, msg);
    }
    public static BaseResponse fail(int result) {
        return new BaseResponse(result);
    }
    public static BaseResponse success() {
        return new BaseResponse(1, "success");
    }
    public static BaseResponse success(String msg) {
        return new BaseResponse(1, msg);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
