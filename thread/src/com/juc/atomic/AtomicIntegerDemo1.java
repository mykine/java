package com.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类AtomicInteger自增
 * 和
 * 普通变量自增，多线程下的安全性对比
 * */
public class AtomicIntegerDemo1 implements  Runnable{

    //被final修饰的对象，对象的引用不能变，但是对象本身的内容依然可以变化
    public static final AtomicInteger  atomicInteger = new AtomicInteger();

    public static  int normalValue = 0;

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo1 r = new AtomicIntegerDemo1();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("原子值="+atomicInteger.get());
        System.out.println("普通值="+normalValue);
    }

    public void incrementAtomic(){
        atomicInteger.getAndIncrement();
    }

    public void incrementNormal(){
        normalValue++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementNormal();
        }
    }

}
