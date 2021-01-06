package com.thread.javavolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile提供可见性、禁止指令重排2个作用
 * volatile不适合场景1： Volatile不适用a++
 * 原因：a++本质是3个步骤的操作(1.对a取值;2.对a计算,3对a赋值),步骤2的值计算依赖步骤1的值，多线程下无法保证原子性
 * a++的3个步骤对应JVM中3个指令，多线程操作a++时，CPU执行线程时，可能在线程1执行到指令1后就切换到线程2执行指令1、指令2，
 * 然后又切换到线程1继续执行指令2。。。非原子操作导致乱序操作而数据不一致
 * 变量赋值要依靠前置状态时，用volatile修饰变量无法保证原子性，所以线程不安全
 * */
public class NoVolatile implements Runnable {
    volatile static int a=0;
    static AtomicInteger realA = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        NoVolatile r = new NoVolatile();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("a="+a);
        System.out.println("realA="+realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
