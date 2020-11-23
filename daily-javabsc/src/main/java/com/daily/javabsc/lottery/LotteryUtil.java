package com.daily.javabsc.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 抽奖工具类,概率和可以不等于1
 * 概率为百分数去掉百分号的部分，如10%，则为10
 * 抽奖操作如下：
 * 1.输入抽奖概率集合，【抽奖概率集合为{10.0, 20.0, 30.0}】
 * 2.生成连续集合，       【生成的连续集合为{(0.0, 10.0],(10.0, 30.0],(30.0, 60.0]}】
 * 3.生成随机数，          【生成方法为 random.nextDouble() * maxElement】
 * 4.判断随机数在哪个区间内，返回该区间的index【生成了随机数12.001，则它属于(10.0, 30.0]，返回 index = 1】
 *
 */
public class LotteryUtil {
    private List<ContinuousList> lotteryList;   //概率连续集合
    private double maxElement; 					//这里只需要最大值，最小值默认为0.0

    /**
     * 构造抽奖集合
     * @param list 为奖品的概率
     */
    public LotteryUtil(List<Double> list){
        lotteryList = new ArrayList<ContinuousList>();
        if(list.size() == 0){
            throw new IllegalArgumentException("抽奖集合不能为空！");
        }
        double minElement = 0d;
        ContinuousList continuousList = null;
        for(Double d : list){
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
    public int randomColunmIndex(){
        int index = -1;
        Random r = new Random();
        double d = r.nextDouble() * maxElement;  //生成0-1间的随机数
        if(d == 0d){
            d = r.nextDouble() * maxElement;     //防止生成0.0
        }
        int size = lotteryList.size();
        for(int i = 0; i < size; i++){
            ContinuousList cl = lotteryList.get(i);
            if(cl.isContainKey(d)){
                index = i;
                break;
            }
        }
        if(index == -1){
            throw new IllegalArgumentException("概率集合设置不合理！");
        }
        return index;

    }

    public double getMaxElement() {
        return maxElement;
    }

    public List<ContinuousList> getLotteryList() {
        return lotteryList;
    }
    public void setLotteryList(List<ContinuousList> lotteryList) {
        this.lotteryList = lotteryList;
    }



    //==================================
    /**
     *
     * @param orignalRates  传人每一个奖品概率的集合,(集合的第一个参数是0.0 表示百分比中奖)
     * @return
     */
    public static int lottery(List<Double> orignalRates) {
        if (orignalRates == null || orignalRates.isEmpty()) {
            return -1;
        }
        int size = orignalRates.size();
        // 计算总概率，这样可以保证不一定总概率是1
        double sumRate = 0d;
        for (double rate : orignalRates) {
            sumRate += rate;
        }
        // 计算每个物品在总概率的基础下的概率情况
        List<Double> sortOrignalRates = new ArrayList<Double>(size);
        Double tempSumRate = 0d;
        /*遍历奖品概率的集合，计算每一个奖品的中间区间*/
        for (double rate : orignalRates) {
            tempSumRate += rate;
            sortOrignalRates.add(tempSumRate/sumRate);
        }
        // 根据区块值来获取抽取到的物品索引
        double nextDouble = Math.random();
        sortOrignalRates.add(nextDouble);
        Collections.sort(sortOrignalRates);
        return sortOrignalRates.indexOf(nextDouble);
    }



    //=======================================================
    /**
     * 抽奖，获取中奖奖品
     * @param awardList 奖品及中奖概率列表
     * @return 中奖商品
     */
    public static Award lottery2(List<Award> awardList) throws Exception{
        if(awardList.isEmpty()){
            throw new Exception("奖品列表不能为空");
        }
        //奖品总数
        int size = awardList.size();

        //计算总概率
        double sumProbability = 0d;
        for (Award award : awardList) {
            sumProbability += award.getProbability();
        }

        //计算每个奖品的概率区间
        //例如奖品A概率区间0-0.1  奖品B概率区间 0.1-0.5 奖品C概率区间0.5-1
        //每个奖品的中奖率越大，所占的概率区间就越大
        List<Double> sortAwardProbabilityList = new ArrayList<Double>(size);
        Double tempSumProbability = 0d;
        for (Award award : awardList) {
            tempSumProbability += award.getProbability();
            sortAwardProbabilityList.add(tempSumProbability / sumProbability);
        }

        //产生0-1之间的随机数
        //随机数在哪个概率区间内，则是哪个奖品
        double randomDouble = Math.random();
        //加入到概率区间中，排序后，返回的下标则是awardList中中奖的下标
        sortAwardProbabilityList.add(randomDouble);
        Collections.sort(sortAwardProbabilityList);
        int lotteryIndex = sortAwardProbabilityList.indexOf(randomDouble);
        return awardList.get(lotteryIndex);
    }


}
