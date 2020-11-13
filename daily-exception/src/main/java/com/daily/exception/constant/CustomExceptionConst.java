package com.daily.exception.constant;

/**
 * 自定义异常枚举类
 */
public enum CustomExceptionConst {

    ERROR_303(303, "自定义异常格式不规范，请按照规范格式< key=errorCode:errorMessage >处理"),
    ERROR_600(600, "异常信息无法获取或未定义"),
    ERROR_603(603, "properties信息读取乱码，字符编码转换异常"),
    ERROR_604(604, "读取配置文件，IO异常"),
    ERROR_605(605, "Please add the profile about custom exception configuration."),
    ERROR_606(606, "程序启动类上未初始化异常监听器类"),
    /**
     * 以下是全局异常时所需
     */
    FAILURE_HTTP(20000, "请求失败"),
    FAILURE_SERVLET(20001, "web容器加载异常"),
    FAILURE_DB(20002, "sql交互失败"),
    FAILURE_VALIDATION(20003, "param验证失败"),
    FAILURE_UNKNOWN(20004, "运行时异常"),

    /**
     * 父级是Exception
     */
    ERROR_0(0);

    public int code;

    public String message;


    CustomExceptionConst(int code) {
        this.code = code;
    }

    CustomExceptionConst(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
