package com.daily.rocketmq.producer;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/23
 */
@Component
public class RmqSend {

    @Autowired
    RmqProducer producerUtil;



//   region  普通消息发送：同步、异步、单向
    //todo 消息发送之前将消息记录持久化进数据库

    //region sendSync 同步发送
    public void sendSync(Message message){
        try {
            SendResult sendResult  = this.producerUtil.getProducer().send(message);
            // 同步发送消息，只要不抛异常就是成功。
            if (sendResult != null) {
                //todo 标记同步发送成功
                System.out.println(new Date() + " Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
            }
        }
        catch (Exception e) {
            //todo 标记同步发送失败

            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理。
            System.out.println(new Date() + " Send mq message failed. Topic is:" + message.getTopic());
            e.printStackTrace();
        }
    }
    //endregion



    //region sendAsync 异步发送

    public void sendAsync(Message message){
        // 异步发送消息, 发送结果通过 callback 返回给客户端。
        this.producerUtil.getProducer().sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(final SendResult sendResult) {
                //todo 标记同步发送成功，只需静等回调即可

                // 消息发送成功。
                System.out.println("send message success. topic=" + sendResult.getTopic() + ", msgId=" + sendResult.getMessageId());
            }

            @Override
            public void onException(OnExceptionContext context) {
                //todo 标记同步发送失败

                // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理。
                System.out.println("send message failed. topic=" + context.getTopic() + ", msgId=" + context.getMessageId());
            }
        });
        // 在 callback 返回之前即可取得 msgId。
        System.out.println("send message async. topic=" + message.getTopic() + ", msgId=" + message.getMsgID());
    }
    //endregion



    //region oneWay 单向发送
    public void oneWay(Message message){
        // 由于在 oneway 方式发送消息时没有请求应答处理，一旦出现消息发送失败，则会因为没有重试而导致数据丢失。若数据不可丢，建议选用可靠同步或可靠异步发送方式。
        this.producerUtil.getProducer().sendOneway(message);
    }
    //endregion

// endregion



    //region  sendOrder  顺序消息发送

    /**
     * @param shardingKey   // 分区顺序消息中区分不同分区的关键字段，Sharding Key 与普通消息的 key 是完全不同的概念。
     *                      // 全局顺序消息，该字段可以设置为任意非空字符串。
     * @param message 需要发送的消息，
     *   示例：
           Message msg = new Message(//
                 // Message 所属的 Topic
                 "Order_global_topic",
                  // Message Tag，可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版的服务器过滤
                  "TagA",
                // Message Body 可以是任何二进制形式的数据，消息队列 RocketMQ 版不做任何干预，需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                  "send order global msg".getBytes()
          );

            // 设置代表消息的业务关键属性，请尽可能全局唯一。
            // 以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发。
            // 注意：不设置也不会影响消息正常收发
            msg.setKey(orderId);
     */
    public void sendOrder(Message message,String shardingKey){
        try {
                SendResult sendResult = this.producerUtil.getOrderProducer().send(message, shardingKey);
                // 发送消息，只要不抛异常就是成功
                if (sendResult != null) {
                    //todo 标记同步发送成功
                    System.out.println(new Date() + " Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
                }
        }
        catch (Exception e) {
                //todo 标记同步发送失败

                // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
                System.out.println(new Date() + " Send mq message failed. Topic is:" + message.getTopic());
                e.printStackTrace();
        }
    }
    //endregion




    //region  sendTransaction  发送事务消息
    /**
     * @param message  制定者要发布的消息
     * @param executer  本地事务执行器
     * @param arg 自定义参数，传入执行器的
     */
    //发送半事务以及执行本地事务
    public void sendTransaction(Message message , LocalTransactionExecuter executer, Object arg){
        try {
            SendResult sendResult =  this.producerUtil.getTransactionProducer().send(message, executer, arg);

            // 发送消息，只要不抛异常就是成功
            if (sendResult != null) {
                //todo 标记同步发送成功
                System.out.println(new Date() + " Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
            }

        } catch (Exception e) {
            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
            System.out.println(new Date() + " Send mq message failed. Topic is:" + message.getTopic());
            e.printStackTrace();
        }
        // demo example 防止进程退出（实际使用不需要这样）
//        TimeUnit.MILLISECONDS.sleep(Integer.MAX_VALUE);
    }
    //endregion



    //region  sendDelay 发送延时消息

    /**
     *  发送延时消息
     * @param message 消息
     * @param delayTime 延时时长（单位：ms 毫秒），
     *        延时三秒，示例：long delayTime = System.currentTimeMillis() + 3000;
     */
    public void sendDelay(Message message,long delayTime){
        Producer producer = this.producerUtil.getProducer();
        try {
            // 设置消息需要被投递的时间
            message.setStartDeliverTime(delayTime);
            SendResult sendResult = producer.send(message);
            // 同步发送消息，只要不抛异常就是成功
            if (sendResult != null) {
                //todo 发送成功
                System.out.println(new Date() + " Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
            }
        } catch (Exception e) {
            //todo 发送失败

            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
            System.out.println(new Date() + " Send mq message failed. Topic is:" + message.getTopic());
            e.printStackTrace();
        }
        // 在应用退出前，销毁 Producer 对象<br>
        // 注意：如果不销毁也没有问题
        producer.shutdown();

    }
    //endregion



    //region
    /**
     *  发送定时消息
     * @param message  消息
     * @param timeStr  定时时间，示例 “2016-03-07 16:21:00”
     */
    public void sendTiming(Message message,String timeStr){
        Producer producer = this.producerUtil.getProducer();
        try {
            // 定时消息，单位毫秒（ms），在指定时间戳（当前时间之后）进行投递，例如 2016-03-07 16:21:00 投递。如果被设置成当前时间戳之前的某个时刻，消息将立刻投递给消费者。
            long timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr.trim()).getTime();
            message.setStartDeliverTime(timeStamp);
            SendResult sendResult = producer.send(message);
            // 同步发送消息，只要不抛异常就是成功
            if (sendResult != null) {
                //todo 发送成功
                System.out.println(new Date() + " Send mq message success. Topic is:" + message.getTopic() + " msgId is: " + sendResult.getMessageId());
            }
        }
        catch (Exception e) {
            //todo 发送失败

            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理
            System.out.println(new Date() + " Send mq message failed. Topic is:" + message.getTopic());
            e.printStackTrace();
        }
        // 在应用退出前，销毁 Producer 对象
        // 注意：如果不销毁也没有问题
        producer.shutdown();
    }

    //endregion

}
