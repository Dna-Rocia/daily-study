package com.daily.pattern.flyweight;

/**
 * @Description //具体享元角色类
 * //具体享元角色类ConcreteFlyweight有一个内蕴状态，在本例中一个Character类型的intrinsicState属性代表，它的值应当在享元对象
 * //被创建时赋予。所有的内蕴状态在对象创建之后，就不会再改变了。如果一个享元对象有外蕴状态的话，所有的外部状态都必须存储在客户端，
 * //在使用享元对象时，再由客户端传入享元对象。这里只有一个外蕴状态，operation()方法的参数state就是由外部传入的外蕴状态。
 * @Author ROCIA
 * @Date 2020/10/29
 */
public class ConcreteFlyweight implements Flyweight {

    private Character intrinsicState = null;

    /**
     * 构造函数，内蕴状态作为参数传入
     *
     * @param intrinsicState
     */
    public ConcreteFlyweight(Character intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    /**
     * 外部状态再怎么变动，内部状态不会发生变化
     *
     * @param state
     */
    @Override
    public void operation(String state) {
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}

