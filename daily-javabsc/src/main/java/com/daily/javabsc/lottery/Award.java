package com.daily.javabsc.lottery;

/**
 * 奖品实体类
 */
public class Award {

    public Award(){}
    public Award(String awardTitle,double probability){
        this.awardTitle = awardTitle;
        this.probability = probability;
    }
    /**奖品ID**/
    private String awardId;
    /**奖品名**/
    private String awardTitle;
    /**中奖概率**/
    private double probability;
    public String getAwardId() {
        return awardId;
    }
    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }
    public String getAwardTitle() {
        return awardTitle;
    }
    public void setAwardTitle(String awardTitle) {
        this.awardTitle = awardTitle;
    }
    public double getProbability() {
        return probability;
    }
    public void setProbability(double probability) {
        this.probability = probability;
    }

}
