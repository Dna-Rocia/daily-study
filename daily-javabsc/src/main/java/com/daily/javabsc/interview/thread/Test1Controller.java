package com.daily.javabsc.interview.thread;

/**
 * @author Rocia
 * @description :
 * @CreateTime 2018-08-13-16:41
 */
public class Test1Controller {

    private int j;

    public static void main(String[] a) {
        Test1Controller controller = new Test1Controller();
        Inc inc = controller.new Inc();
        Dec dec = controller.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();
        }


    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName() + "-inc:" + j);
    }


    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName() + "-dec:" + j);
    }


    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                inc();
            }
        }
    }

    class Dec implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                dec();
            }
        }
    }
}
