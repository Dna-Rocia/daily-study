package com.daily.javabsc.bsc;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/20
 */
public class BscHttp {

    public static void main(String[] args) {
        BscHttp bscHttp = new BscHttp();
//        String url = "https%3A%2F%2Fm.333.com%2FoldDances%3Fcallback%3DbzccaL5brMAo14LRQW_pPRtcEtWHH5X3G5GNHP5dCpPg4DSlYS-FxVvO-72MqKslEppSAl4d8WcGy_PwHhaVi7mUoLIB5peXjxa1kNRT1b6dJtFYfzW0w7heOt9_Oz7KsyfFIS_B9aD45UtlIcFQjvh2L2KBsJpszJ6ar3UeIaDzPFunaDjYQnddTK4fu5lW";
        String url ="http://zt.333.com/html/drawing/mszh1026.html#bxjjms-mszh-w1951?bd_vid=11865472087283966895#B_vid=11865472087283966895";
        //解析Url
//        String url = "http://ad.partner.gifshow.com/track/activate?=a&b=";//?event_type=43&callback=DHAJASALKFyk1uCKBYCyXp-iIDS-uHDd_a5SJ9Dbwkqv46dahahd87TW7hhkJkd&event_time=1597218583732";
//        Map a = HttpUtil.analyzeGETMethodUrl(url);
//        System.out.println(a);
        try {
           String s = bscHttp.analyzeGETUrlForAdmin(url);
           System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 解析
     * @param url
     * @return
     */
    public String analyzeGETUrlForAdmin(String url)throws Exception{
        String value = "";
        try {
            if (!url.contains("://")){
                url = URLDecoder.decode(url,"UTF-8");
            }
            if (url.contains("?")){
                url = url.replace("?","$");
                String[] splitArr = url.split("\\$");
                value = splitArr[0];
            }else {
                value = url;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return URLEncoder.encode(value,"UTF-8");
    }



}
