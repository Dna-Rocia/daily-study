package com.daily.javabsc.bsc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/14
 */
public class BscDate {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  nowDate = new Date();
//        Date ol = null; //new Date("2020-10-13 20:00:00");
//        try {
//            ol = dateFormat.parse("2020-10-13 20:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        assert ol != null;  //assert 断言只适用于调试，切勿使用在业务逻辑中
        Calendar ol = Calendar.getInstance();
        if (ol != null) {
//            System.out.println(ol.before(nowDate));
//            System.out.println("____________________________");
//            System.out.println(ol.after(nowDate));

            System.out.println(ol.getTime().compareTo(nowDate));
//            System.out.println("____________________________");
//            System.out.println(ol.compareTo(nowDate));

        }
    }
}
