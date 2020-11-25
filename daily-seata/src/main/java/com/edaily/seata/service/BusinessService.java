package com.edaily.seata.service;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/23
 */
public interface BusinessService {

    void purchase(String userId, String commodityCode, int orderCount);
}
