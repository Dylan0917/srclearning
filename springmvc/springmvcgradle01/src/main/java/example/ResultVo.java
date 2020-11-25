package example;

import java.io.Serializable;

/**
 * @author yu.wenhua
 * @desc 统一异常处理
 * @date 2020/11/24 17:33
 */

public class ResultVo<T> implements Serializable {
    private static final long serialVersionUID = -3188827052531714695L;

    /**
     * 错误码
     */
    private Integer code;
    /**
     *提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
