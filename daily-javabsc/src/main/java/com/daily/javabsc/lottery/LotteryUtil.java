package com.daily.javabsc.lottery;

import java.util.*;

public class LotteryUtil {
    private List<ContinuousList> lotteryList;   //概率连续集合
    private double maxElement;                    //这里只需要最大值，最小值默认为0.0

    /**
     * 构造抽奖集合
     *
     * @param list 为奖品的概率
     */
    public LotteryUtil(List<Double> list) {
        lotteryList = new ArrayList<ContinuousList>();
        if (list.size() == 0) {
            throw new IllegalArgumentException("抽奖概率集合不能为空！");
        }
        double minElement = 0d;
        ContinuousList continuousList = null;
        for (Double d : list) {
            minElement = maxElement;
            maxElement = maxElement + d;
            continuousList = new ContinuousList(minElement, maxElement);
            lotteryList.add(continuousList);
        }
    }


    /**
     * 进行抽奖操作
     * 返回：奖品的概率list集合中的下标
     */
    public int lottery() {
        int index = -1;
        Random r = new Random();
        double d = r.nextDouble() * maxElement;  //生成0-1间的随机数
        if (d == 0d) {
            d = getRandomNum(0);     //防止生成0.0
        }
        int size = lotteryList.size();
        for (int i = 0; i < size; i++) {
            ContinuousList cl = lotteryList.get(i);
            if (cl.isContainKey(d)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("概率集合设置不合理！");
        }
        return index;
    }

    /**
     * 防止随机数 r.nextDouble()为 0的情况
     *
     * @param count 递归次数
     * @return 返回伪随机数
     */
    private double getRandomNum(int count) {
        Random random = new Random();
        if (count > 10) {
            double abc = random.nextFloat() * maxElement;
            return abc == 0d ? 99d : abc;
        }
        double d = random.nextDouble() * maxElement;
        if (d == 0d) {
            count++;
            d = getRandomNum(count);     //防止生成0.0
        }
        return d;
    }

    public double getMaxElement() {
        return maxElement;
    }


    public static void main(String[] args) {
        //构造概率集合
        List<Double> list = new ArrayList<Double>();
        list.add(20d);
        list.add(30d);
        list.add(50d);
        Lottery01Util ll = new Lottery01Util(list);
        double sumProbability = ll.getMaxElement();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < 10000; i++){
            int index = ll.lottery();
            if(map.containsKey(index)){
                map.put(index, map.get(index) + 1);
            }else{
                map.put(index, 1);
            }
        }
        for(int i = 0; i < list.size(); i++){
            double probability = list.get(i) / sumProbability;
            list.set(i, probability);
        }
        TestLottery.iteratorMap(map, list);
    }

}