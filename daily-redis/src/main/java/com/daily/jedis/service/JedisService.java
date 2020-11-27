package com.daily.jedis.service;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/27
 */
public interface JedisService {

    void set(String key, String value);

    void set(byte[] key, byte[] value);

    void set(String key, String value, int seconds);

    void set(byte[] key, byte[] value, int seconds);

    String get(String key);

    byte[] get(byte[] key);

    void remove(String key);

    void remove(byte[] key);

    void lpush(String key, String... strings);

    void lrem(String key, long count, String value);

    void sadd(String key, String value, int seconds);

    Long incr(String key);

    Long decr(String key);


}
