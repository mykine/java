package com.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lockInterruptibly()方法无限时间尝试获取锁,可以通过调用thread.interrupt()手动中断尝试
 * */
public class LockInterruptibly implements Runnable {

    public static void main(String[] args) {
        LockInterruptibly r = new LockInterruptibly();
        Thread thread0 = new Thread(r,"thread0");
        Thread thread1 = new Thread(r,"thread1");
        thread0.start();
        thread1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();
    }

    private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"尝试获取锁");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName()+"获取到了锁");
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName()+"休眠的时候被中断");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println(Thread.currentThread().getName()+"尝试获取锁的时候被中断");

        }
    }
}
