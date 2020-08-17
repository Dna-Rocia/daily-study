package com.daily.shiro.mapper;


import com.daily.shiro.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by idea
 * description :
 *
 * @author Loyaill
 * @version 2.0.0
 * CreateDate 2018-05-07-15:15
 * @since 1.8JDK
 */
public interface UserMapper {

    User findByUsername(@Param("username") String username);


}
