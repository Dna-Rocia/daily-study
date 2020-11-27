package com.daily.jedis.service.impl;

import com.daily.common.utils.EmptyUtil;
import com.daily.jedis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/8/5
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    @Override
    public boolean existsKey(String key) {
        if (EmptyUtil.isNotEmpty(key)){
            return redisTemplate.hasKey(key);
        }
        return false;
    }

    /**
     * 重命名 key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    @Override
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 重命名 key，如果newKey不存在，才重命名
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    @Override
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除指定key的value
     *
     * @param key
     */
    @Override
    public void deleteKey(String key) {
        //如果key存在，那么就会执行删除操作
        if(existsKey(key)){
            redisTemplate.delete( key );
        }
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    @Override
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    @Override
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = new HashSet<>(keys);
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    @Override
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    @Override
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    @Override
    public long getKeyExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    @Override
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }

    /**
     *    读取缓存
     */
    @Override
    public Object get(String key){
        ValueOperations<String,String> operations=redisTemplate.opsForValue();
        return operations.get( key );
    }

    /**
     * 写入缓存，不带时间
     */
    @Override
    public  boolean  set(String key, String value) {
        boolean flag = false;
        try {
            ValueOperations<String,String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 写入缓存，带时间
     * @param key key
     * @param value value
     * @param expireTime 过期时长，单位是秒
     */
    @Override
    public boolean set(String key, String value, Long expireTime) {
        boolean flag = false;
        try {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


}
