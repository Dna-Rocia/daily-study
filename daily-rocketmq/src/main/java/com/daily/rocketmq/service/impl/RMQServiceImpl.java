package com.daily.rocketmq.service.impl;

import com.aliyun.openservices.ons.api.Message;
import com.daily.rocketmq.conf.AliRmqConfig;
import com.daily.rocketmq.conf.LocalTransactionCheckAndExecuter;
import com.daily.rocketmq.producer.RmqSend;
import com.daily.rocketmq.service.RMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/17
 */
@Service
public class RMQServiceImpl implements RMQService {

    @Autowired
    private RmqSend sendUtil;
    @Autowired
    private AliRmqConfig conf;

    @Override
    public void sendGeneral(String tag) {
        Message message = new Message(this.conf.getTopic_general(), tag, "1", "普通消息测试".getBytes());
        this.sendUtil.sendAsync(message);
    }

    @Override
    public void sendOrder(String topic, String tag) {
        Message message = new Message(topic, tag, "2", "分区顺序消息测试".getBytes());
        this.sendUtil.sendOrder(message, "A");
    }

    @Override
    public void sendTransaction(String tag) {
        Message message = new Message(this.conf.getTopic_transaction(), tag, "3", "事务消息测试".getBytes());
        this.sendUtil.sendTransaction(message, new LocalTransactionCheckAndExecuter(), "2");
    }


}
