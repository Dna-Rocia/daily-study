package com.daily.jedis.service.impl;

import com.daily.jedis.conf.JedisConfig;
import com.daily.jedis.service.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/27
 */
@Service
public class JedisServiceImpl implements JedisService {

    @Autowired
    private JedisConfig jedisConfig;

    @Override
    public void set(String key, String value) {
        this.jedisConfig.set(key,value);
    }

    @Override
    public void set(byte[] key, byte[] value) {
        this.jedisConfig.set(key, value);
    }

    @Override
    public void set(String key, String value, int seconds) {
        this.jedisConfig.set(key, value, seconds);
    }

    @Override
    public void set(byte[] key, byte[] value, int seconds) {
        this.jedisConfig.set(key, value, seconds);
    }

    @Override
    public String get(String key) {
        return this.jedisConfig.get(key);
    }

    @Override
    public byte[] get(byte[] key) {
        return this.jedisConfig.get(key);
    }

    @Override
    public void remove(String key) {
        this.jedisConfig.remove(key);
    }

    @Override
    public void remove(byte[] key) {
        this.jedisConfig.remove(key);
    }

    @Override
    public void lpush(String key, String... strings) {
        this.jedisConfig.lpush(key);
    }

    @Override
    public void lrem(String key, long count, String value) {
        this.jedisConfig.lrem(key,count,value);
    }

    @Override
    public void sadd(String key, String value, int seconds) {
        this.jedisConfig.sadd(key,value,seconds);
    }

    @Override
    public Long incr(String key) {
       return this.jedisConfig.incr(key);
    }

    @Override
    public Long decr(String key) {
        return this.jedisConfig.decr(key);
    }
}
