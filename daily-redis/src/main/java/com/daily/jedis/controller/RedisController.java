package com.daily.jedis.controller;

import com.daily.jedis.service.JedisService;
import com.daily.jedis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/29
 */
@RestController
@RequestMapping
public class RedisController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisService jedisService;
    @Autowired
    private RedisService redisService;


    //region
    @RequestMapping("/setJedis")
    public void setJedis() {
        this.jedisService.set("he", "Hello");
        logger.info("我往redis添加了一个value: " + this.jedisService.get("he") + "    success!!!");
    }
    //endregion

    @RequestMapping("/setRedis")
    public void setRedis() {
        this.redisService.set("she", "Hello");
        logger.info("我往redis添加了一个value: " + this.redisService.get("she") + "    success!!!");
    }
}
