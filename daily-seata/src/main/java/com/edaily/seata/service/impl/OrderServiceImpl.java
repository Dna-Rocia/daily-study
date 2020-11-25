package com.edaily.seata.service.impl;

import com.edaily.seata.dao.OrderDao;
import com.edaily.seata.pojo.Order;
import com.edaily.seata.service.AccountService;
import com.edaily.seata.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDAO;

    private AccountService accountService;

    public Order create(String userId, String commodityCode, int orderCount) {

        int orderMoney = calculate(commodityCode, orderCount);

        accountService.debit(userId, orderMoney);

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(orderCount);
        order.setMoney(orderMoney);

        // INSERT INTO orders ...
        return orderDAO.insert(order);
    }


    private int calculate(String commodityId, int orderCount) {
        return 200 * orderCount;
    }


}