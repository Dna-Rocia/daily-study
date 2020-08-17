package com.daily.shiro.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by idea
 * description :
 *
 * @author Loyaill
 * @version 2.0.0
 * CreateDate 2018-05-07-15:10
 * @since 1.8JDK
 */
public class User {

    private Integer uid;
    private String username;
    private String password;
    private Set<Role> roles = new HashSet<>();

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
