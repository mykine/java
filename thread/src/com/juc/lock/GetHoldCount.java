package com.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入：是指同一个线程中可以直接重复获取已经得到的锁，每次重复获取锁时锁计数器就会+1，每次解锁时计数器就-1,直到锁计数器的值为零了，就代表该线程释放锁了
 * */
public class GetHoldCount {

    static ReentrantLock lock1 = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock1.getHoldCount());//打印获取锁的次数
        lock1.lock();
        System.out.println(lock1.getHoldCount());
        lock1.lock();
        System.out.println(lock1.getHoldCount());
        lock1.lock();
        System.out.println(lock1.getHoldCount());
        lock1.unlock();
        System.out.println(lock1.getHoldCount());
        lock1.unlock();
        System.out.println(lock1.getHoldCount());
        lock1.unlock();
        System.out.println(lock1.getHoldCount());
    }

}
