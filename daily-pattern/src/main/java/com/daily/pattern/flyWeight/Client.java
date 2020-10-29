package com.daily.pattern.flyWeight;

import com.daily.pattern.state.LiftState;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description

//客户端类

 * @Author ROCIA
 * @Date 2020/10/29
 */
public class Client {

    public static void main(String[] args) {
        Client client = new Client();
//        client.flyweight();
//        client.compositeFlyweight();
        client.test();
    }

    private void test(){
        String s = "1,2,10,3";
        String[] sarr = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (String str:sarr ) {
            list.add(Integer.parseInt(str));
        }
        System.out.println(list.contains(1));
    }

    private void flyweight(){
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory(new Character('a'));
        fly.operation("First Call");

        fly = factory.factory(new Character('b'));
        fly.operation("Second Call");

        fly = factory.factory(new Character('a'));
        fly.operation("Third Call");
    }


    private void compositeFlyweight(){
        List<Character> compositeState = new ArrayList<Character>();
        compositeState.add('a');
        compositeState.add('b');
        compositeState.add('c');
        compositeState.add('a');
        compositeState.add('b');

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
