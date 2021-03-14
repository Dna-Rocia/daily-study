package com.daily.javabsc.bsc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/11
 */
public class BscString {

    public static void main(String[] args) {
        BscString bscString = new BscString();
        bscString.test();
    }

    private void test() {
        String s = "1,2,10,3";
        String[] sarr = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (String str : sarr) {
            list.add(Integer.parseInt(str));
        }
        System.out.println(list.contains(1));
    }

}
