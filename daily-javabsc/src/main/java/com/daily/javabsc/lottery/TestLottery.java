package com.daily.javabsc.lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestLottery {


    static final int TIME = 1000000;


    public static void main(String[] args) {
        TestLottery t = new TestLottery();
        try {
            t.lotteryTest02();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void lotteryTest04() throws Exception {
        List<Award> awardList = new ArrayList<Award>();
        awardList.add(new Award("10个积分",0.35d));
        awardList.add(new Award("33个积分",0.25d));
        awardList.add(new Award("5元红包",0.002d));
        awardList.add(new Award("20元话费",0.003d));
        awardList.add(new Award("京东100元购物卡",0.0005d));
        awardList.add(new Award("未中奖",0.1d));

        Map<String,Integer> result = new HashMap<String,Integer>();
        for(int i=0;i<10000;i++){
            Award award = Lottery01Util.lottery2(awardList);
            String title = award.getAwardTitle();
            Integer count = result.get(title);
            result.put(title, count == null ? 1 : count + 1);
        }

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + ", count=" + entry.getValue() +", reate="+ entry.getValue()/10000d);
        }
    }


    public void lotteryTest03(){
        //构造概率集合
        List<Double> list = new ArrayList<Double>();
        list.add(20d);
        list.add(30d);
        list.add(50d);
        int a=0,b=0,c=0;
        for (int i = 0; i < 100 ; i++) {
            int tmp = Lottery01Util.lottery(list);
            if (tmp == 0){
                a++;
            }else if ( tmp == 1){
                b++;
            }else if (tmp == 2){
                c++;
            }
        }
        System.out.println("一等奖（20%）:"+a+" 次，二等奖（30%）:"+b+" 次，三等奖（50%）:"+c+" 次");
    }

    /**
     * 更细致
     */
    public void lotteryTest02(){
        //构造概率集合
        List<Double> list = new ArrayList<Double>();
        list.add(20d);
        list.add(30d);
        list.add(50d);
        Lottery01Util ll = new Lottery01Util(list);
        double sumProbability = ll.getMaxElement();

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < TIME; i++){
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
        iteratorMap(map, list);
    }


    public static void iteratorMap(Map<Integer, Integer> map, List<Double> list){
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int index = entry.getKey();
            int time  = entry.getValue();
            LotteryResult result = new LotteryResult(index, TIME, time, list.get(index));
            System.out.println(result);
        }
    }



    public void lotteryTest01() {
//        /*awardItems获取奖品的一个集合*/
//        if (activityUserDao.getCountByOpenId(Award.WHEEL_AWARD_TYPE, wid, open_id) <= 0) {
//            /* awardItems获取奖品的一个集合 */
//            List<Award> awardItems = awardDao.getByActivity(aw.getWheel_id(), Award.WHEEL_AWARD_TYPE);
//            /* lotterys存放每一个奖品的中奖概率集合 */
//            List<Double> lotterys = new ArrayList<Double>();
//            /* 获取总的奖品数量 */
//            int count = 0;
//            for (Award a : awardItems) {
//                count += a.getProvide_count();
//            }
//            if (aw.getPeople_count() <= count) {
//                lotterys.add(0.0); // 100%中奖
//            } else {
//                /* 预计参与人数减去奖品数 除以参与人数 = 未中奖概率 */
//                lotterys.add((double) (aw.getPeople_count() - count) / (double) aw.getPeople_count());
//            }
//            /* 遍历奖品集合，获取每一个奖品中奖概率 */
//            for (Award a : awardItems) {
//                if (a.getOver_count() > 0) {
//                    lotterys.add((double) a.getProvide_count() / (double) aw.getPeople_count());
//                } else {
//                    lotterys.add(0.0);
//                }
//            }
//            // 计算中奖概率
//            int index = LotteryUtil.lottery(lotterys);
//            if (index > 0) {// 中奖
//                Award a = awardItems.get(index - 1);
//                long key = Math.round(Math.random() * (999999 - 100000) + 100000); // 6位数中奖序列号
//                // 修改商品剩余数量 + 记录序列号
//                if (awardDao.doLowerOverCount(a.getAward_id()) > 0
//                        && activityUserDao.doInsert(new ActivityUser(aw.getPublic_id(), Award.WHEEL_AWARD_TYPE, wid, a.getAward_id(), key + "", open_id)) > 0) {
//                    rb.setCode(index);
//                    rb.setData(key);
//                    rb.setMessage(a.getAward_name());
//                } else {
//                    rb.setCode(0);
//                }
//            }
//            // 抽奖记录
//            activityRecordDao.doInsert(new ActivityRecord(open_id, Award.WHEEL_AWARD_TYPE, wid, request.getRemoteAddr()));
        }
}
