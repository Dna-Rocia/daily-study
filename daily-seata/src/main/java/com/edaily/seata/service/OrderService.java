package com.edaily.seata.service;

import com.edaily.seata.pojo.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}