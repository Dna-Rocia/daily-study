package com.daily.rocketmq.producer;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.daily.rocketmq.conf.AliRmqConfig;
import com.daily.rocketmq.conf.LocalTransactionCheckAndExecuter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description 生产者实例的封装
 * @Author ROCIA
 * @Date 2020/7/17
 */
@Component
public class RmqProducer implements InitializingBean, DisposableBean {

    @Autowired
    private AliRmqConfig aliRmqConfig;

    private Producer producer = null;

    private OrderProducer orderProducer = null;

    private TransactionProducer transactionProducer = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("消息发布者实例，开始实例化 ！！！");
        this.producer = this.createProducerInstance();
        this.orderProducer = this.createOrderProducerInstance();
        this.transactionProducer = this.createTransactionProducerInstance(new LocalTransactionCheckAndExecuter());
        System.out.println("消息发布者实例，结束实例化 ！！！");
    }


    /**
     * // 在应用退出前，销毁 Producer 对象。
     * // 注意：如果不销毁也没有问题。 producer.shutdown();
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("消息发布者<running>实例，开始销毁 ！！！");
        if (this.producer.isStarted()) this.producer.shutdown();
        if (this.orderProducer.isStarted()) this.orderProducer.shutdown();
        if (this.transactionProducer.isStarted()) this.transactionProducer.shutdown();
        System.out.println("消息发布者<running>实例，结束销毁！！！");
    }


    //region   构建参数

    //region getDefaultProducerProperties(默认)

    public Properties getDefaultProducerProperties() {
        Properties properties = new Properties();
        /**
         * Group ID，客户端ID
         */
        properties.put(PropertyKeyConst.GROUP_ID, aliRmqConfig.getGroupID_TCP_protocol());
        /**
         * 消息队列服务接入点
         */
        properties.put(PropertyKeyConst.ONSAddr, aliRmqConfig.getONSAddr());
        /**
         * AccessKey, 用于标识、校验用户身份
         */
        properties.put(PropertyKeyConst.AccessKey, aliRmqConfig.getAccessKey());
        /**
         * SecretKey, 用于标识、校验用户身份
         */
        properties.put(PropertyKeyConst.SecretKey, aliRmqConfig.getSecretKey());
        /**
         * Name Server地址
         */
        properties.put(PropertyKeyConst.NAMESRV_ADDR, aliRmqConfig.getNAMESRV_ADDR());
        /**
         * 消息发送超时时间，如果服务端在配置的对应时间内未ACK，则发送客户端认为该消息发送失败。
         */
        properties.put(PropertyKeyConst.SendMsgTimeoutMillis, aliRmqConfig.getSendMsgTimeoutMillis());
        /**
         * 设置客户端接入来源，默认ALIYUN
         */
        properties.put(PropertyKeyConst.OnsChannel, "ALIYUN");
        return properties;
    }

    //endregion


    //region getProducerProperties（普通消息）

    public Properties getProducerProperties() {
        return getDefaultProducerProperties();
    }
    //endregion


    //region  getTransactionProperties（事务消息）

    public Properties getTransactionProperties() {
        Properties properties = getDefaultProducerProperties();
        /**
         * 设置事务消息的第一次回查延迟时间
         */
        properties.put(PropertyKeyConst.CheckImmunityTimeInSeconds, aliRmqConfig.getCheckImmunityTimeInSeconds());
        return properties;
    }
    //endregion


    //region getOrderProducerProperties（顺序消息）

    public Properties getOrderProducerProperties() {
        Properties properties = getDefaultProducerProperties();
        /**
         * 消息消费失败时的最大重试次数
         */
        properties.put(PropertyKeyConst.MaxReconsumeTimes, aliRmqConfig.getMaxReconsumeTimes());
        /**
         * 顺序消息消费失败进行重试前的等待时间 单位(毫秒)
         */
        properties.put(PropertyKeyConst.SuspendTimeMillis, aliRmqConfig.getSuspendTimeMillis());

        return properties;
    }

    //endregion


    //endregion


    //region   createProducerInstance 创建Producer

    /**
     * 返回创建的{@link Producer}实例是线程安全, 可复用, 发送各个主题. 一般情况下, 一个进程中构建一个实例足够满足发送消息的需求.
     */
    private Producer createProducerInstance() {
        DefaultMQProducer a = new DefaultMQProducer();
        a.setRetryTimesWhenSendFailed(1);
        this.producer = ONSFactory.createProducer(getProducerProperties());
        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
        this.producer.start();
        return this.producer;
    }

    //endregion


    //region  createOrderProducerInstance 创建顺序Producer
    private OrderProducer createOrderProducerInstance() {
        this.orderProducer = ONSFactory.createOrderProducer(getOrderProducerProperties());
        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
        this.orderProducer.start();
        return this.orderProducer;
    }

    //endregion


    //region  getTransactionProducerInstance   创建事务Producer
    private TransactionProducer createTransactionProducerInstance(LocalTransactionChecker LocalTransactionChecker) {
        this.transactionProducer = ONSFactory.createTransactionProducer(getTransactionProperties(), LocalTransactionChecker);
        // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可
        this.transactionProducer.start();
        return this.transactionProducer;
    }

    //endregion


    public Producer getProducer() {
        return producer;
    }

    public OrderProducer getOrderProducer() {
        return orderProducer;
    }

    public TransactionProducer getTransactionProducer() {
        return transactionProducer;
    }
}
