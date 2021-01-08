package com.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁(读锁)-排他锁(写锁)：实际是同一个锁，在读场景下，变成读锁定，允许多个线程同时得到锁进行读操作，
 * 在写场景下，切换成写锁定，只允许一个线程得到锁进行写操作，其他线程等待释放出的写锁
 * */
public class CinemaReadWrite {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    private static void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获得读锁");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁");
        }
    }

    private static void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获得写锁,正在写入");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
        }
    }

    public static void main(String[] args) {
        new Thread(()->read(),"线程1").start();
        new Thread(()->read(),"线程2").start();
        new Thread(()->write(),"线程3").start();
        new Thread(()->write(),"线程4").start();
    }

}
