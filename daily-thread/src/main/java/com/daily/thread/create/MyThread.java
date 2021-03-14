package com.daily.thread.create;

/**
 * 定义一个Thread 类的子类
 */
public class MyThread extends Thread{

    //重写run().
    @Override
    public void run() {
        //run()方法体就是Thread子类执行的业务
//        super.run();
        System.out.println("当前是Thread 子类的run()");
    }
}
