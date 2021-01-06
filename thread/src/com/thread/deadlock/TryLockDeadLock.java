package com.thread.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用tryLock来避免死锁
 * */
public class TryLockDeadLock implements  Runnable{

    public static void main(String[] args) {
        TryLockDeadLock r1 = new TryLockDeadLock();
        r1.flag = 1 ;
        TryLockDeadLock r2 = new TryLockDeadLock();
        r2.flag = 2 ;
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();

    }

    int flag = 0;

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    @Override
    public void run() {
        for (int i = 0; i < 1000 ; i++) {
            if(flag==1){
                try {
                    if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName()+"获取到lock1");
                        if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                            System.out.println(Thread.currentThread().getName()+"获取到lock2，拥有两把锁了");
                            System.out.println(Thread.currentThread().getName()+"操作作业");
                            Thread.sleep(300);
                            lock2.unlock();
                            lock1.unlock();
                            System.out.println(Thread.currentThread().getName()+"操作完毕后释放lock1和lock2");
                            break;
                        }else{
                            System.out.println(Thread.currentThread().getName()+"尝试获取lock2失败");
                            lock1.unlock();
                            System.out.println(Thread.currentThread().getName()+"尝试获取lock2失败后释放lock1");
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+"尝试获取lock1失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    if(lock2.tryLock(3000,TimeUnit.MILLISECONDS)){
                        System.out.println(Thread.currentThread().getName()+"获取到lock2");
                        if(lock1.tryLock(3000,TimeUnit.MILLISECONDS)){
                            System.out.println(Thread.currentThread().getName()+"获取到lock1，拥有两把锁了");
                            System.out.println(Thread.currentThread().getName()+"操作作业");
                            Thread.sleep(200);
                            lock1.unlock();
                            lock2.unlock();
                            System.out.println(Thread.currentThread().getName()+"操作完毕后释放lock1和lock2");
                            break;
                        }else{
                            System.out.println(Thread.currentThread().getName()+"尝试获取lock1失败");
                            lock2.unlock();
                            System.out.println(Thread.currentThread().getName()+"尝试获取lock1失败后释放lock2");
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName()+"尝试获取lock2失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
