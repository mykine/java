package com.thread.javasynchronized;

/**
 * 同时访问同步方法和非同步方法,非同步方法不受影响
 * */
public class SynchronizedYesAndNo6 implements Runnable {

    static SynchronizedYesAndNo6 instance= new SynchronizedYesAndNo6();

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

    public void method2(){
        System.out.println("我是没加锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束-我是没加锁的方法");
    }
}
