package com.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock不会像synchronized那样，异常的时候自动释放锁，所以需要在finally中主动释放锁
 * 以便保证发生异常时锁一定被释放
 * */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获取锁执行");
        }finally {
            lock.unlock();
        }
    }

}
