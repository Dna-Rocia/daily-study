package com.daily.pattern.decoractor;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/30
 */
public class Coffee {

    String make(String water){
        return water +"咖啡";
    }

    Double cost(){
        return 10d;
    }

}
