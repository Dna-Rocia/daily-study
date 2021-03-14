package com.daily.thread.controller;

import com.daily.thread.model.ThreadP1;

public class TestController {

    public static void main(String[] args) {
        TestController test = new TestController();
        test.p1Test();



    }


    //1.学习线程创建、启动
    private void p1Test(){
        System.out.println("JVM启动的main线程，TestController.main()");
        //创建线程对象
        ThreadP1 thread = new ThreadP1();

        //启动线程
        thread.start();

        //其后的逻辑代码
        System.out.println("这是 TestController.main() 后边的代码");

        /*
         ps：调用线程的start()来启动线程，启动线程的实质就是请求jvm运行相应的线程，这个线程具体在什么时候运行由线程调度器（Scheduler）决定
         注意：
            start()调用结束并不意味着子线程开始运行，只是告知线程调度器已准备好；
            新开启的线程会执行run()；
            如果开启了多个线程，start（）调用的顺序并不一定就是线程启动的顺序；
            多线程运行结果与 代码执行/调用顺序无关
         */
    }

}
