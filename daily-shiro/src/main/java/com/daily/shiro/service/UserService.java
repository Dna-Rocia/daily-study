package com.daily.shiro.service;


import com.daily.shiro.model.User;

/**
 * Created by idea
 * description :
 *
 * @author Loyaill
 * @version 2.0.0
 * CreateDate 2018-05-07-15:16
 * @since 1.8JDK
 */
public interface UserService {

    User findByUsername(String username);


}
