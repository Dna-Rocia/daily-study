package com.daily.exception.handle;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/12
 */
public class CustomError extends Error {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误对象
     */
    private Object[] args;

    /**
     * @param message 异常提示信息
     */
    public CustomError(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * @param message 异常提示信息
     * @param cause   异常对象
     */
    public CustomError(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * @param message 异常提示信息
     * @param args    在抛异常时把某些数据也抛给异常处理者
     */
    public CustomError(Integer code, String message, Object... args) {
        super(message);
        this.code = code;
        this.message = message;
        this.args = args;
    }

    /**
     * @param message 异常提示信息
     * @param cause   异常对象
     * @param args    在抛异常时把某些数据也抛给异常处理者
     */
    public CustomError(Integer code, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.args = args;
    }

    //在捕获并处理异常时，获取异常的详细信息。比如在filter层统一处理异常时通过以下方法获取所处理异常的信息来在view层作出应答
    public String getMessage() {
        return message;
    }


    public Object[] getArgs() {
        return args;
    }

}
