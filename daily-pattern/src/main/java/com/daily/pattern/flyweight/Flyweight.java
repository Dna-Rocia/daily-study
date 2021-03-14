package com.daily.pattern.flyweight;

/**
 * @Description 享元接口，通过这个接口传入外部状态并作用于外部状态；
 * @Author ROCIA
 * @Date 2020/10/29
 */
public interface Flyweight {

    //一个示意性方法，参数state是外蕴状态
    void operation(String state);

}
