package com.daily.rocketmq.conf;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionChecker;
import com.aliyun.openservices.ons.api.transaction.LocalTransactionExecuter;
import com.aliyun.openservices.ons.api.transaction.TransactionStatus;
import com.daily.common.utils.HashUtil;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/27
 * 回查机制示例
 */
public class LocalTransactionCheckAndExecuter implements LocalTransactionChecker, LocalTransactionExecuter {

    @Override
    public TransactionStatus check(Message msg) {
        //消息 ID（有可能消息体一样，但消息 ID 不一样，当前消息属于半事务消息，所以消息 ID 在控制台无法查询）
        String msgId = msg.getMsgID();
        //消息体内容进行 crc32，也可以使用其它的方法如 MD5
        long crc32Id = HashUtil.crc32Code(msg.getBody());
        //消息 ID、消息本 crc32Id 主要是用来防止消息重复
        //如果业务本身是幂等的，可以忽略，否则需要利用 msgId 或 crc32Id 来做幂等
        //如果要求消息绝对不重复，推荐做法是对消息体使用 crc32 或 MD5 来防止重复消息
        //业务自己的参数对象，这里只是一个示例，需要您根据实际情况来处理
//        Object businessServiceArgs = new Object();
        TransactionStatus transactionStatus = TransactionStatus.Unknow;
        try {
            //todo  自定义业务回查机制
//            boolean isCommit = businessService.checkbusinessService(businessServiceArgs);

            boolean isCommit = true;


            if (isCommit) {
                //本地事务已成功则提交消息
                transactionStatus = TransactionStatus.CommitTransaction;
            } else {
                //本地事务已失败则回滚消息
                transactionStatus = TransactionStatus.RollbackTransaction;
            }
        } catch (Exception e) {
//            log.error("Message Id:{}", msgId, e);
        }
//        log.warn("Message Id:{}transactionStatus:{}", msgId, transactionStatus.name());
        return transactionStatus;


    }


    @Override
    public TransactionStatus execute(Message msg, Object arg) {
        // 消息 ID（有可能消息体一样，但消息 ID 不一样，当前消息 ID 在控制台无法查询）
        String msgId = msg.getMsgID();
        // 消息体内容进行 crc32，也可以使用其它的如 MD5
        long crc32Id = HashUtil.crc32Code(msg.getBody());
        // 消息 ID 和 crc32id 主要是用来防止消息重复
        // 如果业务本身是幂等的，可以忽略，否则需要利用 msgId 或 crc32Id 来做幂等
        // 如果要求消息绝对不重复，推荐做法是对消息体 body 使用 crc32 或 MD5 来防止重复消息
        Object businessServiceArgs = new Object();
        TransactionStatus transactionStatus = TransactionStatus.Unknow;
        try {
            boolean isCommit = true; //businessService.execbusinessService(businessServiceArgs);
            if (isCommit) {
                // 本地事务成功则提交消息
                transactionStatus = TransactionStatus.CommitTransaction;
            } else {
                // 本地事务失败则回滚消息
                transactionStatus = TransactionStatus.RollbackTransaction;
            }
        } catch (Exception e) {
//            log.error("Message Id:{}", msgId, e);
        }
        System.out.println(msg.getMsgID());
//        log.warn("Message Id:{}transactionStatus:{}", msgId, transactionStatus.name());
        return transactionStatus;
    }


}
