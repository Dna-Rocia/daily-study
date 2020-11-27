package com.daily.jedis.property;

import com.daily.common.utils.AESUtil;
import com.daily.common.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/8/17
 */
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource(value = "classpath:/pro/redis.properties")
public class RedisProperties {

    // Redis服务器IP
    @Value("${redis.ip}")
    private String ip;

    // Redis的端口号
    @Value("${redis.port}")
    private Integer port;

    // 访问密码
    @Value("${redis.password}")
    private String password;
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。

    @Value("${redis.max_active}")
    private Integer max_active;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    @Value("${redis.max_idle}")
    private Integer max_idle;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    @Value("${redis.max_wait}")
    private Integer max_wait;

    // 超时时间
    @Value("${redis.timeout}")
    private Integer timeout;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (EmptyUtil.isNotEmpty(password)){
            password = AESUtil.aesDecode(password);
            System.out.println("redis 密码："+ password);
        }
        this.password = password;
    }

    public Integer getMax_active() {
        return max_active;
    }

    public void setMax_active(Integer max_active) {
        this.max_active = max_active;
    }

    public Integer getMax_idle() {
        return max_idle;
    }

    public void setMax_idle(Integer max_idle) {
        this.max_idle = max_idle;
    }

    public Integer getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(Integer max_wait) {
        this.max_wait = max_wait;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "RedisConf{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", max_active=" + max_active +
                ", max_idle=" + max_idle +
                ", max_wait=" + max_wait +
                ", timeout=" + timeout +
                '}';
    }
}

