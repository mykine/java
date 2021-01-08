package com.juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock支持升级，不支持降级
 * 因为写锁和读锁不能同时存在，写锁被锁定前所有读锁必须都释放，
 * 所以如果降级，多个同时持有读锁的线程都进行升级时，会邀请对方都先释放读锁，造成死锁
 * */
public class Upgrading {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();


    private static void readUp(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获得读锁");
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"读锁尝试升级成写锁");
                writeLock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"读锁成功升级成写锁");
                }finally {
                    writeLock.lock();
                    System.out.println(Thread.currentThread().getName()+"读锁成功升级成写锁后,释放写锁");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放读锁");
        }
    }

    private static void writeDown(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获得写锁,正在写入");
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"写锁尝试降级为读锁");
                readLock.lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"写锁成功降级为读锁");
                }finally {
                    readLock.unlock();
                    System.out.println(Thread.currentThread().getName()+"写锁成功降级为读锁后,释放读锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放写锁");
        }
    }

    public static void main(String[] args) {
//        new Thread(()->readUp(),"线程1").start();
        new Thread(()->writeDown(),"线程2").start();

    }

}
