package com.daily.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/26
 */
public class DateUtil {

    public static String convertDateToStr(Date date,String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
       return format.format(date);
    }

}
