package com.daily.thread.model;

/**
 * 自定义线程类
 *  分别在构造方法中和run()中打印当前线程信息
 */
public class MethodThreadP1 extends Thread{


    public MethodThreadP1() {
        System.out.println("自定义线程子类线程已启动，MethodThreadP1构造方法，当前线程名称："+Thread.currentThread().getName());

    }

    @Override
    public void run() {
        System.out.println("自定义线程子类线程已启动，MethodThreadP1.run(),当前线程名称："+Thread.currentThread().getName());

    }
}
