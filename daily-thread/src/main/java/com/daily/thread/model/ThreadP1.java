package com.daily.thread.model;

/**
 * 定义一个Thread 类的子类
 */
public class ThreadP1 extends Thread{

    //重写run().
    @Override
    public void run() {
        //run()方法体就是Thread子类执行的业务
//        super.run();
        System.out.println("自定义线程子类线程已启动，ThreadP1.run()");
    }
}
