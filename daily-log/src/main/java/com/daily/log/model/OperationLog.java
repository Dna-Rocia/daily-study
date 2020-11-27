package com.daily.log.model;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/28
 */
public class OperationLog extends BaseModel {

    private String operaId;

    private String operaModel;

    private String operaType;

    private String operaDesc;

    private String operaRespParam; //返回结果

    private String operaReqParam;// 请求参数


    public OperationLog() {
    }


    public String getOperaRespParam() {
        return operaRespParam;
    }

    public void setOperaRespParam(String operaRespParam) {
        this.operaRespParam = operaRespParam;
    }

    public String getOperaReqParam() {
        return operaReqParam;
    }

    public void setOperaReqParam(String operaReqParam) {
        this.operaReqParam = operaReqParam;
    }

    public String getOperaId() {
        return operaId;
    }

    public void setOperaId(String operaId) {
        this.operaId = operaId;
    }

    public String getOperaModel() {
        return operaModel;
    }

    public void setOperaModel(String operaModel) {
        this.operaModel = operaModel;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public String getOperaDesc() {
        return operaDesc;
    }

    public void setOperaDesc(String operaDesc) {
        this.operaDesc = operaDesc;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "operaId='" + operaId + '\'' +
                ", operaModel='" + operaModel + '\'' +
                ", operaType='" + operaType + '\'' +
                ", operaDesc='" + operaDesc + '\'' +
                ", operaRespParam='" + operaRespParam + '\'' +
                ", operaReqParam='" + operaReqParam + '\'' +
                '}';
    }
}
