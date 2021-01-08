package com.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用TryLock来避免死锁
 * */
public class TryLockDeadLock implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        TryLockDeadLock r1 = new TryLockDeadLock();
        TryLockDeadLock r2 = new TryLockDeadLock();
        r1.flag = 1;
        r2.flag = 2;
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    int flag = 1;

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    @Override
    public void run() {
        if(1==flag){
            try {
                if(lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                      try {
                          System.out.println(Thread.currentThread().getName()+"拿到lock1");
                          Thread.sleep(100);
                          if(lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                              try{
                                  System.out.println(Thread.currentThread().getName()+"拿到lock2");
                                  System.out.println(Thread.currentThread().getName()+"已经拥有两把锁，执行任务");
                                  Thread.sleep(100);
                              }finally {
                                  lock2.unlock();
                                  System.out.println(Thread.currentThread().getName()+"释放lock2");
                              }
                          }else{
//                              lock1.unlock();
                              System.out.println(Thread.currentThread().getName()+"获取lock2失败");
                          }
                      }finally {
                          lock1.unlock();
                          System.out.println(Thread.currentThread().getName()+"释放lock1");
                      }
                }else{
                    System.out.println(Thread.currentThread().getName()+"获取lock1失败");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"获取lock1发生异常");
            }
        }else{
            try {
                if(lock2.tryLock(800, TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println(Thread.currentThread().getName()+"拿到lock2");
                            Thread.sleep(100);
                            if(lock1.tryLock(800,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println(Thread.currentThread().getName()+"拿到lock1");
                                    System.out.println(Thread.currentThread().getName()+"已经拥有两把锁，执行任务");
                                    Thread.sleep(100);
                                }finally {
                                    lock1.unlock();
                                    System.out.println(Thread.currentThread().getName()+"释放lock1");
                                }
                            }else{
//                                lock2.unlock();
                                System.out.println(Thread.currentThread().getName()+"获取lock1失败");
                            }
                        }finally {
                            lock2.unlock();
                            System.out.println(Thread.currentThread().getName()+"释放lock2");
                        }
                }else{
                    System.out.println(Thread.currentThread().getName()+"获取lock2失败");
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"获取lock2发生异常");
            }
        }
    }
}
