package com.daily.jedis.service;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/8/5
 */
public interface RedisService {

    /**
     * 判断key值是否存在
     *
     * @param key
     * @return
     */
    boolean existsKey(String key);

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey 旧key
     * @param newKey 新key
     */
    void renameKey(String oldKey, String newKey);

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey 旧key
     * @param newKey 新key
     * @return
     */
    boolean renameKeyNotExist(String oldKey, String newKey);

    /**
     * 删除key
     *
     * @param key 将要删除的key
     */
    void deleteKey(String key);

    /**
     * 删除多个key
     *
     * @param keys 多个key
     */
    void deleteKey(String... keys);

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    void deleteKey(Collection<String> keys);

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    void expireKey(String key, long time, TimeUnit timeUnit);

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    void expireKeyAt(String key, Date date);

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    long getKeyExpire(String key, TimeUnit timeUnit);

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    void persistKey(String key);

    /**
     * 读取缓存
     */
    Object get(String key);

    /**
     * 写入缓存，不带时间
     */
    boolean set(String key, String value);

    /**
     * 写入缓存，带时间
     */
    boolean set(String key, String value, Long expireTime);

}
