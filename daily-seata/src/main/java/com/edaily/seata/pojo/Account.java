package com.edaily.seata.pojo;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/23
 */
public class Account {

    private String userId;
    private Integer id;
    private Integer money;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
