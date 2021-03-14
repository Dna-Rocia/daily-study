package com.daily.javabsc.bsc;

import java.math.BigDecimal;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/26
 */
public class BscCompare {

    private void bigDecimalCompare() {
        //大数字类型比较 0：相等  -1：小于  1 大于
        BigDecimal zero = new BigDecimal(0);
        BigDecimal a = new BigDecimal(-2);
        BigDecimal b = new BigDecimal(3);
        BigDecimal c = new BigDecimal(0.00);
        System.out.println(a.subtract(zero) + "     =========" + b.subtract(zero) + " =========== " + c.subtract(zero));
        System.out.println(zero.subtract(b));
    }


}
