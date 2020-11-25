package com.daily.javabsc.lottery;

import com.daily.common.utils.RandomUtil;

import java.util.Random;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/24
 */
public class TestRandom {

    public static void main(String[] args) {
        TestRandom testRandom = new TestRandom();
//        testRandom.testRandom01();
//        testRandom.testRandom02();
//        testRandom.testRandom03();
//        testRandom.testRandom04();
//      double a =  testRandom.testRandom05() ;
      long curr = System.currentTimeMillis();
        Random ra = new Random(curr);
        for (int i = 0; i < 10; i++) {
            System.out.println("ra.nextGaussian():"+ra.nextGaussian() +"\t ra.nextDouble():"+ra.nextDouble());
        }


        System.out.println("================================================");

        Random rb = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("rb.nextGaussian():"+rb.nextGaussian() +"\trb.nextDouble():"+rb.nextDouble());
        }


    }

    public void testRandom06(){
        Random ra = new Random();
        for (int i = 0; i < 20; i++) {
            double gas = ra.nextGaussian();
            if (gas < 0 || gas > 1){
              continue;
            }
            System.out.println(gas);
            break;
        }
    }


    public double testRandom05(){
        Random ra = new Random();
        double gas = ra.nextGaussian();
       if (gas < 0 || gas > 1){
           return testRandom05();
       }
       return gas;
    }


    public void testRandom04(){
//        int i = RandomUtil.nextEvenInt(1,10);
        int  i = RandomUtil.nextInt(0,3);
        System.out.println(i);
    }


    public void testRandom03(){
        double[][] d =  RandomUtil.next2DDoubleArray(1,5);
        for (double[] sx :d  ) {
            for (double s:sx ) {
                System.out.println(s);
            }
        }
    }


    public void testRandom02(){
        String d =  RandomUtil.nextAlphabeticString(10);
        System.out.println(d);
    }


    public void testRandom01(){
        double[] d =  RandomUtil.nextDoubleArray(3);
        for (double sx :d  ) {
            System.out.println(sx);
        }
    }

}
