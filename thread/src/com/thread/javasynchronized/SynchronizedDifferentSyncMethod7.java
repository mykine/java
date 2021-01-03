package com.thread.javasynchronized;

/**
 * 同时访问一个类的不同的普通同步方法,本质上synchronized的锁的是this这个实例，
 * 这个实例的多个同步方法不能同时运行
 * */
public class SynchronizedDifferentSyncMethod7 implements Runnable {

    static SynchronizedDifferentSyncMethod7 instance= new SynchronizedDifferentSyncMethod7();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){

        }
        System.out.println("Finished");
    }


    @Override
    public void run() {

        if(Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }else{
            method2();
        }
    }

    public synchronized void method1(){
        System.out.println("我是加锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束-我是加锁的方法");
    }

    public synchronized void method2(){
        System.out.println("我是没加锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束-我是没加锁的方法");
    }
}
