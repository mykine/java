package com.thread.javavolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  volatile适合场景2：当作触发器使用,本质上也是纯赋值操作
 *  用volatile修饰变量可以保证原子性，volatile本身可以保证可见性，所以线程是安全的
 * */
public class UseVolatile2 implements Runnable {
    static int a = 0;
    static int b = 0;
    volatile static boolean hasOK=false;
    static AtomicInteger realA = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        UseVolatile2 r = new UseVolatile2();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        r.printAB();
        System.out.println("hasOK="+hasOK);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            b++;
            if(a>1000&&b>1000){
                hasOK=true;
            }
            realA.incrementAndGet();
        }
    }


    public void printAB(){
        if(hasOK){
            System.out.println("a="+a+",b="+b);
        }
    }
}
