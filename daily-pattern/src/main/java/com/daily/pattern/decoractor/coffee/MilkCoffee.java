package com.daily.pattern.decoractor.coffee;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/30
 */
public class MilkCoffee {
    private Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public String make(String water) {
        return this.coffee.make(water);
    }

    public Double cost() {
        return this.coffee.cost() + 2;
    }


}














