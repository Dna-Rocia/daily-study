package com.daily.exception.handle;

import com.daily.exception.constant.CustomExceptionConst;
import com.daily.exception.utils.PropertiesFileUtil;

import java.io.IOException;

public class CustomException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public CustomException() {
    }

    /**
     * 根据键查找出对应的异常信息
     *
     * @param key 关联配置文件错误-键
     */
    public CustomException(String key) {
        setCodeAndMsg(key, 0, null);
    }

    /**
     * @param key     关联配置文件错误-键
     * @param readWay 获取方式 0-字节方式获取，1-利用资源文件处理
     *                默认0
     */
    public CustomException(String key, int readWay) {
        setCodeAndMsg(key, readWay, null);
    }

    /**
     * @param key         关联配置文件错误-键
     * @param charsetName 字符编码
     */
    public CustomException(String key, String charsetName) {
        setCodeAndMsg(key, 0, charsetName);
    }

    /**
     * @param key         关联配置文件错误-键
     * @param readWay     获取方式 0-字节方式获取，1-利用资源文件处理
     *                    默认0
     * @param charsetName 字符编码
     */
    public CustomException(String key, int readWay, String charsetName) {
        setCodeAndMsg(key, readWay, charsetName);
    }

    /**
     * 根据键查找出对应的异常信息
     *
     * @param key  关联配置文件错误-键
     * @param data 附加数据
     */
    public CustomException(String key, Object data) {
        setCodeAndMsg(key, 0, null);
        this.data = data;
    }

    /**
     * @param key     关联配置文件错误-键
     * @param readWay 获取方式 0-字节方式获取，1-资源文件处理
     *                默认0
     */
    public CustomException(String key, int readWay, Object data) {
        setCodeAndMsg(key, readWay, null);
        this.data = data;
    }

    /**
     * @param key         关联配置文件错误-键
     * @param charsetName 字符编码
     * @param data        附加数据
     */
    public CustomException(String key, String charsetName, Object data) {
        setCodeAndMsg(key, 0, charsetName);
        this.data = data;
    }

    /**
     * @param key         关联配置文件错误-键
     * @param readWay     获取方式 0-字节方式获取，1-资源文件处理
     *                    默认0
     * @param charsetName 字符编码
     * @param data        附加数据
     */
    public CustomException(String key, int readWay, String charsetName, Object data) {
        setCodeAndMsg(key, readWay, charsetName);
        this.data = data;
    }

    public CustomException(Integer code, String errorMsg) {
        this.message = errorMsg;
        this.code = code;
    }

    public CustomException(CustomExceptionConst constant) {
        this.message = constant.getMessage();
        this.code = constant.code;
    }

    public CustomException(CustomExceptionConst constant, Throwable errorCourse) {
        super(constant.getMessage(), errorCourse);
        this.code = code;
    }

    public CustomException(String message, Integer code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    /**
     * set错误码，以及错误信息
     *
     * @param key     关联配置文件错误-键
     * @param readWay 获取方式 0-字节方式获取，1-利用资源文件处理
     *                默认0
     */
    private void setCodeAndMsg(String key, int readWay, String charsetName) {
        String[] valArr;
        if (readWay == 0) {
            valArr = getInputStreamValByKey(key, charsetName);
        } else {
            valArr = getValByKey(key, charsetName);
        }
        this.code = Integer.parseInt(valArr[0]);
        this.message = valArr[1];
    }


    private String[] getValByKey(String key, String charsetName) {
        PropertiesFileUtil pfu = null;
        pfu = PropertiesFileUtil.getInstance();
        String value = pfu.get(key, charsetName).trim();
//        if (value.length() == 0)throw new CustomException(CustomExceptionConst.ERROR_600);
        String[] valArr = value.split("\\:");
        if (valArr.length != 2) {
            throw new CustomException(CustomExceptionConst.ERROR_303);
        }
        return valArr;

    }

    /**
     * 字节方式读取配置文件
     *
     * @param key
     * @return
     */
    private String[] getInputStreamValByKey(String key, String charsetName) {
        String value = "";
        try {
            value = PropertiesFileUtil.readPropertiesFile(key, charsetName);
        } catch (IOException io) {
            throw new CustomException(CustomExceptionConst.ERROR_IO);
        }
        String[] valArr = value.split("\\:");
        if (valArr.length != 2) {
            throw new CustomException(CustomExceptionConst.ERROR_303);
        }
        return valArr;
    }


    @Override
    public String toString() {
        return "异常信息:[code=" + code + ", message=" + message + "]";
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 覆盖fillInStackTrace()方法，抹掉异常中的堆栈跟踪信息
     */
    public synchronized Throwable fillInStackTrace() {
        return this;
    }


}
