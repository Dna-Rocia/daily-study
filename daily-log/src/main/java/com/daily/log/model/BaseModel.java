package com.daily.log.model;

import java.util.Date;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/28
 */
public class BaseModel {

    private Integer operaUserId;

    private String operaUserName;

    private String operaURI;

    private String operaIP;

    private String operaMethod;

    private String operaVer; // 操作版本

    private Date operaCreateTime;


    public String getOperaMethod() {
        return operaMethod;
    }

    public void setOperaMethod(String operaMethod) {
        this.operaMethod = operaMethod;
    }

    public Integer getOperaUserId() {
        return operaUserId;
    }

    public void setOperaUserId(Integer operaUserId) {
        this.operaUserId = operaUserId;
    }

    public String getOperaUserName() {
        return operaUserName;
    }

    public void setOperaUserName(String operaUserName) {
        this.operaUserName = operaUserName;
    }

    public String getOperaURI() {
        return operaURI;
    }

    public void setOperaURI(String operaURI) {
        this.operaURI = operaURI;
    }

    public String getOperaIP() {
        return operaIP;
    }

    public void setOperaIP(String operaIP) {
        this.operaIP = operaIP;
    }

    public String getOperaVer() {
        return operaVer;
    }

    public void setOperaVer(String operaVer) {
        this.operaVer = operaVer;
    }

    public Date getOperaCreateTime() {
        return operaCreateTime;
    }

    public void setOperaCreateTime(Date operaCreateTime) {
        this.operaCreateTime = operaCreateTime;
    }
}
