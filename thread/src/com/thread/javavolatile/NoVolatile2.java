package com.thread.javavolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不适合场景2：变量赋值需依靠前置状态时，用volatile修饰变量无法保证原子性，线程不安全
 * */
public class NoVolatile2 implements Runnable {
    volatile static boolean hasOK=false;
    static AtomicInteger realA = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        NoVolatile2 r = new NoVolatile2();
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
        hasOK=!hasOK;
    }
}
