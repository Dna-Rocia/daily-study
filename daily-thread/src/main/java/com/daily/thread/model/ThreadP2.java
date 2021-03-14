package com.daily.thread.model;

/**
 * 定义一个Thread 类的子类
 */
public class ThreadP2 extends Thread{

    @Override
    public void run() {
        System.out.println("自定义线程子类线程已启动，ThreadP2.run()");
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("sub Thread:"+i);
                int time = (int)(Math.random() * 1000); //Math.random() 产生 0~1 之间的随机数
                Thread.sleep(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
