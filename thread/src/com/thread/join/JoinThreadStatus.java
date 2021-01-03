package com.thread.join;

import java.util.concurrent.TimeUnit;

/**
 * join期间，线程的状态，先join再mainThread.getState()
 * */
public class JoinThreadStatus {

    public static void main(String[] args) {
        Thread threadMain =  Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    threadMain.interrupt();
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("join期间，主线程的状态是:"+threadMain.getState());
                    System.out.println(Thread.currentThread().getName()+"运行完毕");
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
