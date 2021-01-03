package com.thread.join;

/**
 * 线程的join()方法可以让主线程等待子线程执行完毕
 * */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            }
        });
        System.out.println(Thread.currentThread().getName()+"开始执行子线程");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getName()+"子线程执行完毕");
    }

}
