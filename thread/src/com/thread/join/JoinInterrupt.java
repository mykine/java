package com.thread.join;

import java.util.concurrent.TimeUnit;

/**
 * 演示join期间被中断的效果
 * */
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread threadMain =  Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    threadMain.interrupt();
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"Finished");
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+"线程中断了");
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"线程中断了");
//            e.printStackTrace();
            thread1.interrupt();
        }
        System.out.println("子线程运行完毕");
    }
}
