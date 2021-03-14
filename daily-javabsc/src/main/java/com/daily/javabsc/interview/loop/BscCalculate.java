package com.daily.javabsc.interview.loop;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/24
 */
public class BscCalculate {

    public static void main(String[] args) {
//       long i = 614613;
////        long i = 11000;
//       double f =  (double)i%5000;
//        System.out.println(i / 5000 + (f > 0 ? 1 : 0));
//        System.out.println(f);

        int maxPageNum = 614661 / 20000;
        int emp = (614661 % 20000) > 0 ? 2 : 0;
        maxPageNum = maxPageNum + emp;

    }


}
