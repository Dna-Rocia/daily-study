package com.daily.pattern.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //客户端类
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
        client.flyweight();
        System.out.println("==================================================");
        client.compositeFlyweight();
    }

    /**
     * 单纯享元模式
     */
    private void flyweight() {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory('a');
        fly.operation("First Call");

        fly = factory.factory('b');
        fly.operation("Second Call");

        fly = factory.factory('a');
        fly.operation("Third Call");
    }

    /**
     * 复合享元模式
     */
    private void compositeFlyweight() {
        List<Character> compositeState = new ArrayList<Character>();
        compositeState.add('c');
        compositeState.add('d');
        compositeState.add('e');
        compositeState.add('c');
        compositeState.add('d');

        FlyweightFactory flyFactory = new FlyweightFactory();
        Flyweight compositeFly1 = flyFactory.factory(compositeState);
        Flyweight compositeFly2 = flyFactory.factory(compositeState);
        compositeFly1.operation("Composite Call");

        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

        Character state = 'a';
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }

}
