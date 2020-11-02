package com.daily.pattern.decoractor.shape;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/11/2
 */
public class Client {

    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());
        Shape redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("没有包装的圆：");
        circle.draw();

        System.out.println("包装后的红色圆：");
        redCircle.draw();

        System.out.println("包装后的矩形：");
        redRectangle.draw();
    }

}
