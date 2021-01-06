package com.thread.javavolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile适合场景1：(32位上的JVM的long、double类型除外)纯赋值，变量赋值无需依靠前置状态时，
 *  用volatile修饰变量可以保证原子性，volatile本身可以保证可见性，所以线程是安全的
 * */
public class UseVolatile implements Runnable {
    volatile static boolean hasOK=false;
    static AtomicInteger realA = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        UseVolatile r = new UseVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("hasOK="+hasOK);
        System.out.println("realA="+realA.get());
    }



    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            ok();
            realA.incrementAndGet();
        }
    }

    private void ok() {
        hasOK=true;
    }
}
