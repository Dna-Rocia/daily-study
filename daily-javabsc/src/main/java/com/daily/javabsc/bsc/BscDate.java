package com.daily.javabsc.bsc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/14
 */
public class BscDate {

    public static void main(String[] args) {
    Date2();

    }



    private static void Date1(){
        Date  nowDate = new Date();
//        assert ol != null;  //assert 断言只适用于调试，切勿使用在业务逻辑中
        Calendar ol = Calendar.getInstance();
        if (ol != null) {
            System.out.println(ol.getTime().compareTo(nowDate));
//            System.out.println("____________________________");
//            System.out.println(ol.compareTo(nowDate));

        }
    }


    private static void Date2(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date  nowDate = new Date();
        Date ol = null; //new Date("2020-10-13 20:00:00");
        try {
            ol = dateFormat.parse("2020-10-13 20:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (ol != null) {
            System.out.println(ol.before(nowDate));
            System.out.println("____________________________");
            System.out.println(ol.after(nowDate));
        }
    }
    static class Critical{
        private BigDecimal min;
        private BigDecimal max;
        private boolean isInterval;

        public Critical() {
        }

        public Critical(BigDecimal min, BigDecimal max, boolean isInterval) {
            this.min = min;
            this.max = max;
            this.isInterval = isInterval;
        }

        public BigDecimal getMin() {
            return min;
        }

        public void setMin(BigDecimal min) {
            this.min = min;
        }

        public BigDecimal getMax() {
            return max;
        }

        public void setMax(BigDecimal max) {
            this.max = max;
        }

        public boolean isInterval() {
            return isInterval;
        }

        public void setInterval(boolean interval) {
            isInterval = interval;
        }
    }


}
