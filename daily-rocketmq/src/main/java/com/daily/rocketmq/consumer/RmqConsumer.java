package com.daily.rocketmq.consumer;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderConsumer;
import com.daily.rocketmq.conf.AliRmqConfig;
import com.daily.rocketmq.consumer.listener.ConsumerGeneralListener;
import com.daily.rocketmq.consumer.listener.ConsumerOrderListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Description 消费者实例封装
 * @Author ROCIA
 * @Date 2020/7/17
 */
@Component
public class RmqConsumer {

    @Autowired
    private AliRmqConfig aliRmqConfig;

    private Consumer consumer;

    private OrderConsumer orderConsumer;

    //region  consumer 参数构建


    //region  getConsumerProperties（普通消息）

    /**
     * 普通消息订阅参数
     *
     * @return
     */
    public Properties getConsumerProperties() {
        Properties properties = getDefaultConsumerProperties();
        /**
         * 消息队列服务接入点
         */
        properties.put(PropertyKeyConst.ONSAddr, aliRmqConfig.getONSAddr().trim());
        /**
         * 消费线程数量
         */
        properties.put(PropertyKeyConst.ConsumeThreadNums, aliRmqConfig.getConsumeThreadNums() == null ? 20 : aliRmqConfig.getConsumeThreadNums());
        /**
         * 设置每条消息消费的最大超时时间,超过这个时间,这条消息将会被视为消费失败,等下次重新投递再次消费. 每个业务需要设置一个合理的值. 单位(分钟)
         */
        properties.put(PropertyKeyConst.ConsumeTimeout, aliRmqConfig.getConsumeTimeout() == null ? 4 : aliRmqConfig.getConsumeTimeout());
        return properties;
    }

    //endregion


    //region getBatchConsumerProperties（批量消息）

    /**
     * 创建BatchConsumer
     *
     * @return
     */
    public Properties getBatchConsumerProperties() {
        Properties properties = getDefaultConsumerProperties();
        /**
         * 消息队列服务接入点
         */
        properties.put(PropertyKeyConst.ONSAddr, aliRmqConfig.getONSAddr());
        /**
         * 消费线程数量
         */
        properties.put(PropertyKeyConst.ConsumeThreadNums, aliRmqConfig.getConsumeThreadNums());
        /**
         * 设置每条消息消费的最大超时时间,超过这个时间,这条消息将会被视为消费失败,等下次重新投递再次消费. 每个业务需要设置一个合理的值. 单位(分钟)
         */
        properties.put(PropertyKeyConst.ConsumeTimeout, aliRmqConfig.getConsumeTimeout());
        /**
         * BatchConsumer每次批量消费的最大消息数量, 默认值为1, 允许自定义范围为[1, 32], 实际消费数量可能小于该值.
         */
        properties.put(PropertyKeyConst.ConsumeMessageBatchMaxSize, aliRmqConfig.getConsumeMessageBatchMaxSize());
        return properties;
    }
    //endregion


    //region getOrderedConsumerProperties（顺序消息)

    /**
     * 顺序消息订阅参数
     *
     * @return
     */
    public Properties getOrderedConsumerProperties() {
        return getConsumerProperties();
    }
    //endregion


    //region  getPullConsumerProperties  （自动拉取消息）

    /**
     * 自动拉取
     *
     * @return
     */
    public Properties getPullConsumerProperties() {
        Properties properties = getDefaultConsumerProperties();
        properties.put(PropertyKeyConst.AUTO_COMMIT, aliRmqConfig.getAUTO_COMMIT());
        return properties;
    }
    //endregion


    //region getDefaultConsumerProperties（消费者默认参数）

    public Properties getDefaultConsumerProperties() {
        Properties properties = new Properties();
        /**
         * Group ID，客户端ID
         */
        properties.put(PropertyKeyConst.GROUP_ID, aliRmqConfig.getGroupID_TCP_protocol().trim());
        /**
         * AccessKey, 用于标识、校验用户身份
         */
        properties.put(PropertyKeyConst.AccessKey, aliRmqConfig.getAccessKey().trim());
        /**
         * SecretKey, 用于标识、校验用户身份
         */
        properties.put(PropertyKeyConst.SecretKey, aliRmqConfig.getSecretKey().trim());
        /**
         * Name Server地址
         */
        properties.put(PropertyKeyConst.NAMESRV_ADDR, aliRmqConfig.getNAMESRV_ADDR().trim());

        /**
         * 集群订阅方式 (默认)
         */
        properties.put(PropertyKeyConst.MessageModel, aliRmqConfig.getMessageModel().trim());

        /**
         * 设置客户端接入来源，默认ALIYUN
         */
        properties.put(PropertyKeyConst.OnsChannel, "ALIYUN");
        return properties;
    }

    //endregion


    //endregion


    //region  subscribeGeneral 消费无序消息

    /**
     * 外部访问
     *
     * @param tag 订阅过滤字符
     *            //     * @param messageListener 消息监听器
     */

    public void subscribeGeneral(String tag) {
        this.consumer = ONSFactory.createConsumer(getConsumerProperties());
        this.consumer.subscribe(aliRmqConfig.getTopic_general(), tag, new ConsumerGeneralListener());
        this.consumer.start();
        System.out.println("普通消息订阅监听器，启动状态：" + this.consumer.isStarted());
    }

    //endregion


    //region  subscribeOrder 消费有序消息

    /**
     * 外部订阅顺序消息
     *
     * @param tag 订阅过滤字符
     *            //     * @param topic  主题（顺序消息：全局/局部）
     *            //     * @param messageOrderListener 监听器
     */
    public void subscribeOrder(String topic, String tag) {
        this.orderConsumer = ONSFactory.createOrderedConsumer(getOrderedConsumerProperties());
        this.orderConsumer.subscribe(topic, tag, new ConsumerOrderListener());
        this.orderConsumer.start();
        System.out.println("订阅全局/分区消息订阅监听器，启动状态：" + this.orderConsumer.isStarted());
    }
    //endregion


    public Consumer getConsumer() {
        return consumer;
    }

    public OrderConsumer getOrderConsumer() {
        return orderConsumer;
    }


    //region
//    你的保证你的rocketMQ是铂金版
//    public void pullSubscribe(){
//        Properties properties = getDefaultConsumerProperties();
//        PullConsumer consumer = ONSFactory.createPullConsumer(properties);
//        // 启动 Consumer
//        consumer.start();
//        // 获取 topic-xxx 下的所有分区
//        Set<TopicPartition> topicPartitions = consumer.topicPartitions(aliRmqConfig.getTopic_partition());
//        // 指定需要拉取消息的分区
//        consumer.assign(topicPartitions);
//
//        while (true) {
//            // 拉取消息，超时时间为 3000 ms
//            List<Message> messages = consumer.poll(3000);
//            System.out.printf("Received message: %s %n", messages);
//        }
//    }
    //endregion


}
