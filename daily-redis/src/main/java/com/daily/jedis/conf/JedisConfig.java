package com.daily.jedis.conf;

import com.daily.jedis.property.RedisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import org.apache.commons.lang.StringUtils;


/**
 * @Description 操作 redis 库方式一
 * jedis 是redis 面向java 的一个客户端
 * @Author ROCIA
 * @Date 2020/8/17
 */
@Component
public class JedisConfig {
    //logger
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisConfig.class);

    /**
     * 将连接的 redis 库的具体连接配置信息
     */
    @Autowired
    private RedisProperties redisProperties;

//	protected static ReentrantLock lockPool = new ReentrantLock();
//	protected static ReentrantLock lockJedis = new ReentrantLock();

    /**
     * jedis 连接池
     */
    private JedisPool jedisPool = null;
    /**
     * 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
     */
    private boolean testOnBorrow = false;

    /**
     * redis过期时间,以秒为单位
     */
    // 一小时
    public final int EXRP_HOUR = 60 * 60;
    // 一天
    public final int EXRP_DAY = 60 * 60 * 24;
    // 一个月
    public final int EXRP_MONTH = 60 * 60 * 24 * 30;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 初始化Redis连接池
     */
    public void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(this.redisProperties.getMax_active());
            config.setMaxIdle(this.redisProperties.getMax_idle());
            config.setMaxWaitMillis(this.redisProperties.getMax_wait());
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(this.testOnBorrow);
            this.jedisPool = new JedisPool(config, this.redisProperties.getIp(), this.redisProperties.getPort(), this.redisProperties.getTimeout());
        } catch (Exception e) {
            LOGGER.error("First create JedisPool error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private synchronized void poolInit() {
        if (null == this.jedisPool) {
            initialPool();
        }
    }


    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized Jedis getJedis() {
        poolInit();
        Jedis jedis = null;
        try {
            if (null != this.jedisPool) {
                jedis = this.jedisPool.getResource();
                try {
                    String reply = jedis.auth(this.redisProperties.getPassword());
                    LOGGER.info("jedis auth reply : " + reply);

                } catch (Exception e) {
                    LOGGER.error("jedis auth error : " + e);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Get jedis error : " + e);
        }
        return jedis;
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized void set(String key, String value) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 设置 byte[]
     *
     * @param key
     * @param value
     */
    public synchronized void set(byte[] key, byte[] value) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 设置 String 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized void set(String key, String value, int seconds) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.setex(key, seconds, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set keyex error : " + e);
        }
    }

    /**
     * 设置 byte[] 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized void set(byte[] key, byte[] value, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Set key error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized String get(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 获取byte[]值
     *
     * @param key
     * @return value
     */
    public synchronized byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        byte[] value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized void remove(String key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Remove keyex error : " + e);
        }
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized void remove(byte[] key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("Remove keyex error : " + e);
        }
    }

    /**
     * lpush
     *
     * @param key
     * @param key
     */
    public synchronized void lpush(String key, String... strings) {
        try {
            Jedis jedis = getJedis();
            jedis.lpush(key, strings);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("lpush error : " + e);
        }
    }

    /**
     * lrem
     *
     * @param key
     * @param count
     * @param value
     */
    public synchronized void lrem(String key, long count, String value) {
        try {
            Jedis jedis = getJedis();
            jedis.lrem(key, count, value);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("lpush error : " + e);
        }
    }

    /**
     * sadd
     *
     * @param key
     * @param value
     * @param seconds
     */
    public synchronized void sadd(String key, String value, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.sadd(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            LOGGER.error("sadd error : " + e);
        }
    }

    /**
     * incr
     *
     * @param key
     * @return value
     */
    public synchronized Long incr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.incr(key);
        jedis.close();
        return value;
    }

    /**
     * decr
     *
     * @param key
     * @return value
     */
    public synchronized Long decr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.decr(key);
        jedis.close();
        return value;
    }
}
