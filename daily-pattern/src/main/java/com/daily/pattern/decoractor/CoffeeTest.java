package com.daily.pattern.decoractor;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/10/30
 */
public class CoffeeTest {


    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        MilkCoffee milkCoffee = new MilkCoffee(coffee);
        String str1 = milkCoffee.make("水");
        Double a1 = milkCoffee.cost();
        SugarCoffee sugarCoffee = new SugarCoffee(coffee);
        String str2 = sugarCoffee.make("水");
        Double a2 = sugarCoffee.cost();

        System.out.println(str1 +" === "+ a1);
        System.out.println(str2 +" === "+ a2);
    }

}
