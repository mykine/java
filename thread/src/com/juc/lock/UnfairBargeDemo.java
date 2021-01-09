package com.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁插队策略
 * 演示：不公平锁-当阻塞队列头部是读线程时，新线程可以插队，
 * 多个线程可以同时得到读锁进行读操作， 写锁只允许一个线程独占，且要求所有读锁线程都已释放读锁
 * 非公平的情况下，当阻塞线程队列的头部是读锁时，新来的读线程可以插队获取读锁同时进行读操作
 * */
public class UnfairBargeDemo {
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    private static void read(){
        System.out.println(Thread.currentThread().getName()+"尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到读锁，读操作...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁");
        }
    }

    private static void write(){
        System.out.println(Thread.currentThread().getName()+"尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获取到写锁，写操作...");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
        }
    }

    public static void main(String[] args) {
        new Thread(()->write(),"thread1").start();
        new Thread(()->read(),"thread2").start();
        new Thread(()->read(),"thread3").start();
        new Thread(()->write(),"thread4").start();
        new Thread(()->read(),"thread5").start();

        new Thread(()->{
            Thread[] threadList = new Thread[1000];
            for (int i = 0; i < threadList.length; i++) {
                threadList[i]=new Thread(()->read(),"子子线程"+(i+1));
            }
            for (int i = 0; i < threadList.length; i++) {
                threadList[i].start();
            }
        }).start();
    }

}
