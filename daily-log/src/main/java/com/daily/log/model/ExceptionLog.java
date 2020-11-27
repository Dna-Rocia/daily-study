package com.daily.log.model;


/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/28
 */
public class ExceptionLog extends BaseModel {

    private String excId;

    private String excReqParam;

    private String excName;

    private String excMessage;


    public String getExcId() {
        return excId;
    }

    public void setExcId(String excId) {
        this.excId = excId;
    }

    public String getExcReqParam() {
        return excReqParam;
    }

    public void setExcReqParam(String excReqParam) {
        this.excReqParam = excReqParam;
    }

    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }
}
