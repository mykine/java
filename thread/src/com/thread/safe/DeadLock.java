package com.thread.safe;

import java.util.concurrent.TimeUnit;

public class DeadLock implements  Runnable {

    Object lock1=new Object() ;
    Object lock2=new Object() ;
    int key = 1;

    public static void main(String[] args) {
        DeadLock r = new DeadLock();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        if(1==key){
            key=2;
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName()+"得到lock1");
                System.out.println(Thread.currentThread().getName()+"尝试获取lock2");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName()+"得到lock2");
                }
            }
        }else{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName()+"得到lock2");
                System.out.println(Thread.currentThread().getName()+"尝试获取lock1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName()+"得到lock1");
                }
            }
        }

    }

}
