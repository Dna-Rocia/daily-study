package com.daily.thread.model;

public class MethodThreadP2 extends Thread{

    public MethodThreadP2() {
        System.out.println("MethodThreadP2 构造方法，Thread.currentThread().getName()："+Thread.currentThread().getName());
        System.out.println("MethodThreadP2 构造方法，this.getName()："+ this.getName()); //指的是构造方法的当前对象
    }

    @Override
    public void run() {
        System.out.println("MethodThreadP2.run()，Thread.currentThread().getName()："+Thread.currentThread().getName());
        System.out.println("MethodThreadP2.run()，this.getName()："+ this.getName());
    }
}
