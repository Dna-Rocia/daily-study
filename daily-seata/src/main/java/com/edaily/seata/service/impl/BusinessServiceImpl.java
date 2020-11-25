package com.edaily.seata.service.impl;

import com.edaily.seata.service.BusinessService;
import com.edaily.seata.service.OrderService;
import com.edaily.seata.service.StorageService;

public class BusinessServiceImpl implements BusinessService {

    private StorageService storageService;

    private OrderService orderService;

    /**
     * 采购
     */
    public void purchase(String userId, String commodityCode, int orderCount) {

        storageService.deduct(commodityCode, orderCount);

        orderService.create(userId, commodityCode, orderCount);
    }
}