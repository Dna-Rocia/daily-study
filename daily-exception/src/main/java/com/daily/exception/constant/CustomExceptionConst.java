package com.daily.exception.constant;

/**
 * 全局异常枚举类
 */
public enum CustomExceptionConst {

    ERROR_101(101, "自定义异常格式不规范，请按照规范格式< key=errorCode:errorMessage >处理"),
    ERROR_102(102, "自定义文件中异常信息无法获取或未定义，请按照规范格式< key=errorCode:errorMessage >进行定义"),
    ERROR_103(103, "properties信息读取乱码，字符编码转换异常"),
    ERROR_104(104, "读取配置文件发生IO异常"),
    ERROR_105(105, "请添加自拟关于自定义异常的文件，并按照规范进行设定处理"),
    ERROR_106(106, "未初始化异常监听器，请在启动时对异常监听器进行初始化"),
    /**
     * 以下是全局异常时所需
     */
    FAILURE_HTTP(200, "请求失败"),
    FAILURE_SERVLET(201, "web容器加载异常"),
    FAILURE_DB(202, "sql交互失败"),
    FAILURE_VALIDATION(203, "参数验证失败"),
    FAILURE_UNKNOWN(204, "运行时未知异常"),

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
