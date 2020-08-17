package com.daily.rocketmq.service;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/17
 */
public interface RMQService {

    void sendGeneral(String tag);


    void sendOrder(String topic, String tag);


    void sendTransaction(String tag);

// 订阅无效
//    void subscribeGeneral(String tag);
//
//    void subscribeOrderGlobal(String tag);
//
//    void subscribeOrderPartition(String tag);

}
