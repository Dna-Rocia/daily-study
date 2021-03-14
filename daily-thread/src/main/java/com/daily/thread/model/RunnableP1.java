package com.daily.thread.model;

/**
 * 当线程类已有父类的情况下，遵循Java单继承原则，就不能继续继承Thread类，但可以实现Runnable接口形式处理
 * 1）定义一个类实现Runnable 接口
 */
public class RunnableP1 implements Runnable{

    //2) 重写Runnable接口中的抽象方法run()
    @Override
    public void run() {
        System.out.println("自定义线程子类线程已启动，RunnableP1.run()");

        //其方法体内就是子线程执行的代码
        for (int i = 0; i < 10; i++) {
            System.out.println("sub Thread:"+i);
        }
    }
}
