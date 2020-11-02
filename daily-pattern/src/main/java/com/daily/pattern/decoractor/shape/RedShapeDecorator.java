package com.daily.pattern.decoractor.shape;

/**
 * @Description 创建继承抽象类的非抽象类
 * @Author ROCIA
 * @Date 2020/11/2
 */
public class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    public void draw(){
        decoratorShape.draw();
        redBorder();
    }

    private void redBorder(){
        System.out.println("，颜色是：红色的");
    }

}
