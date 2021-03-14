package com.daily.javabsc.bsc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/20
 */
public class BscCover {

    public static void main(String[] args) {
        arr2List();
    }


    private static void arr2List() {
        String s = "1,4,5,7,89";
        String[] arr = s.split(",");
//       List<String> list =  Arrays.asList(arr);
//       System.out.println(list);
        List<Long> ids = new ArrayList<>();
        for (String id : arr) {
            ids.add(Long.parseLong(id));
        }
        System.out.println(ids);
    }
}
