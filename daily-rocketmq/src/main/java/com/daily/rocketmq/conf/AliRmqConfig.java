package com.daily.rocketmq.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/15
 */
@Component
@ConfigurationProperties(prefix = "rmq")
@PropertySource(value = "classpath:/conf/rocketmq.properties")
public class AliRmqConfig {

//    region   mq基本参数

    /*阿里云身份验证 AccessKeyId */
    private String accessKey;

    /*阿里云身份验证 AccessKeySecret */
    private String secretKey;

    /*设置 TCP 接入域名 */
    private String NAMESRV_ADDR;

//    endregion


//    region  控制台创建的 Group ID，用于对消费者或生产者实例进行分类

    /*TCP协议 */
    private String groupID_TCP_protocol;

    /*Http协议 */
    private String groupID_HTTP_protocol;

//    endregion


//    region  消息主题，一级消息类型，通过 Topic 对消息进行分类

    /*普通消息*/
    private String topic_general;

    /*事务消息*/
    private String topic_transaction;

    /*全局顺序消息*/
    private String topic_global;

    /*局部顺序消息*/
    private String topic_partition;

    /*定时/延时消息*/
    private String topic_timing;


//    endregion


//    region  交互时效设置

    /*设置发送超时时间，单位毫秒 */
    private Long sendMsgTimeoutMillis;

    private String ONSAddr; //在producer上会被nameSERV_addr 覆盖；在orderProducer未使用到； 在transactionProducer上未被覆盖

    private Long suspendTimeMillis;

    private Long maxReconsumeTimes;

    private Long checkImmunityTimeInSeconds;

//    endregion


//    region
    /**
     * 消费线程数量
     */
    private Long consumeThreadNums;

    /**
     * 设置每条消息消费的最大超时时间 单位（分钟）
     */
    private Long consumeTimeout;

    /**
     * 每次批量消费的最大消息数量, 默认值为1, 允许自定义范围为[1, 32], 实际消费数量可能小于该值
     */
    private Long consumeMessageBatchMaxSize;

    /**
     * 是否开启自动订阅
     */
    private String AUTO_COMMIT;


    private String MessageModel;


//    endregion


    public String getMessageModel() {
        return MessageModel;
    }

    public void setMessageModel(String messageModel) {
        MessageModel = messageModel;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getNAMESRV_ADDR() {
        return NAMESRV_ADDR;
    }

    public String getGroupID_TCP_protocol() {
        return groupID_TCP_protocol;
    }

    public String getGroupID_HTTP_protocol() {
        return groupID_HTTP_protocol;
    }

    public String getTopic_general() {
        return topic_general;
    }

    public String getTopic_transaction() {
        return topic_transaction;
    }

    public String getTopic_global() {
        return topic_global;
    }

    public String getTopic_partition() {
        return topic_partition;
    }

    public String getTopic_timing() {
        return topic_timing;
    }

    public Long getSendMsgTimeoutMillis() {
        return sendMsgTimeoutMillis;
    }

    public String getONSAddr() {
        return ONSAddr;
    }

    public Long getSuspendTimeMillis() {
        return suspendTimeMillis;
    }

    public Long getMaxReconsumeTimes() {
        return maxReconsumeTimes;
    }

    public Long getCheckImmunityTimeInSeconds() {
        return checkImmunityTimeInSeconds;
    }

    public Long getConsumeThreadNums() {
        return consumeThreadNums;
    }

    public Long getConsumeTimeout() {
        return consumeTimeout;
    }

    public Long getConsumeMessageBatchMaxSize() {
        return consumeMessageBatchMaxSize;
    }

    public String getAUTO_COMMIT() {
        return AUTO_COMMIT;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setNAMESRV_ADDR(String NAMESRV_ADDR) {
        this.NAMESRV_ADDR = NAMESRV_ADDR;
    }

    public void setGroupID_TCP_protocol(String groupID_TCP_protocol) {
        this.groupID_TCP_protocol = groupID_TCP_protocol;
    }

    public void setGroupID_HTTP_protocol(String groupID_HTTP_protocol) {
        this.groupID_HTTP_protocol = groupID_HTTP_protocol;
    }

    public void setTopic_general(String topic_general) {
        this.topic_general = topic_general;
    }

    public void setTopic_transaction(String topic_transaction) {
        this.topic_transaction = topic_transaction;
    }

    public void setTopic_global(String topic_global) {
        this.topic_global = topic_global;
    }

    public void setTopic_partition(String topic_partition) {
        this.topic_partition = topic_partition;
    }

    public void setTopic_timing(String topic_timing) {
        this.topic_timing = topic_timing;
    }

    public void setSendMsgTimeoutMillis(Long sendMsgTimeoutMillis) {
        this.sendMsgTimeoutMillis = sendMsgTimeoutMillis;
    }

    public void setONSAddr(String ONSAddr) {
        this.ONSAddr = ONSAddr;
    }

    public void setSuspendTimeMillis(Long suspendTimeMillis) {
        this.suspendTimeMillis = suspendTimeMillis;
    }

    public void setMaxReconsumeTimes(Long maxReconsumeTimes) {
        this.maxReconsumeTimes = maxReconsumeTimes;
    }

    public void setCheckImmunityTimeInSeconds(Long checkImmunityTimeInSeconds) {
        this.checkImmunityTimeInSeconds = checkImmunityTimeInSeconds;
    }

    public void setConsumeThreadNums(Long consumeThreadNums) {
        this.consumeThreadNums = consumeThreadNums;
    }

    public void setConsumeTimeout(Long consumeTimeout) {
        this.consumeTimeout = consumeTimeout;
    }

    public void setConsumeMessageBatchMaxSize(Long consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }

    public void setAUTO_COMMIT(String AUTO_COMMIT) {
        this.AUTO_COMMIT = AUTO_COMMIT;
    }

    @Override
    public String toString() {
        return "AliRocketMQConf{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", NAMESRV_ADDR='" + NAMESRV_ADDR + '\'' +
                ", groupID_TCP_protocol='" + groupID_TCP_protocol + '\'' +
                ", groupID_HTTP_protocol='" + groupID_HTTP_protocol + '\'' +
                ", topic_general='" + topic_general + '\'' +
                ", topic_transaction='" + topic_transaction + '\'' +
                ", topic_global='" + topic_global + '\'' +
                ", topic_partition='" + topic_partition + '\'' +
                ", topic_timing='" + topic_timing + '\'' +
                ", sendMsgTimeoutMillis=" + sendMsgTimeoutMillis +
                ", ONSAddr='" + ONSAddr + '\'' +
                ", suspendTimeMillis=" + suspendTimeMillis +
                ", maxReconsumeTimes=" + maxReconsumeTimes +
                ", checkImmunityTimeInSeconds=" + checkImmunityTimeInSeconds +
                ", consumeThreadNums=" + consumeThreadNums +
                ", consumeTimeout=" + consumeTimeout +
                ", consumeMessageBatchMaxSize=" + consumeMessageBatchMaxSize +
                ", AUTO_COMMIT='" + AUTO_COMMIT + '\'' +
                '}';
    }


}
