package com.daily.thread.method;

import com.daily.thread.model.MethodThreadP1;

/**
 * 学习线程的常用方法
 */
public class ThreadCommonMethodTest {

    public static void main(String[] args) {
        ThreadCommonMethodTest test = new ThreadCommonMethodTest();
        test.methodThreadP1Test();

    }

    private void methodThreadP1Test(){
       System.out.println("JVM启动的main线程，TestController.main()，当前线程名称："+Thread.currentThread().getName());

       //创建子线程对象
       MethodThreadP1 p1 = new MethodThreadP1();

       //启动子线程，这块只是就绪阶段，并未真正启动
//       p1.start();
       /* 情况一：未忽略掉 p1.start();
       输出结果：
            JVM启动的main线程，TestController.main()，当前线程名称：main
            自定义线程子类线程已启动，MethodThreadP1构造方法，当前线程名称：main
            自定义线程子类线程已启动，MethodThreadP1.run(),当前线程名称：Thread-0

       总结：
         调用子线程MethodThreadP1的构造方法，在main线程中调用构造方法，所以构造方法中的当前线程是 main线程
         启动子线程，子线程会调用自己定义的run()，所以run()当当前线程就是 Thread-0线程
       */

        p1.run();
        /* 情况二：忽略掉 p1.start()，直接执行run()；
        输出结果：
            JVM启动的main线程，TestController.main()，当前线程名称：main
            自定义线程子类线程已启动，MethodThreadP1构造方法，当前线程名称：main
            自定义线程子类线程已启动，MethodThreadP1.run(),当前线程名称：main

        总结：在main()中调用run()，没有开启新线程，所以结果始终都是main线程
        */


    }

}
