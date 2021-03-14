package com.daily.pattern.decorator.coffee;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/30
 */
public class SugarCoffee {

    private Coffee coffee;

    public SugarCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    String make(String water) {
        return this.coffee.make(water) + "糖";
    }

    Double cost() {
        return this.coffee.cost() + 1;
    }

}
