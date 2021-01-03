package com.thread.javasynchronized;

/**
 * 同时访问一个类的不同的静态同步方法和非静态同步方法,
 * 静态同步方法锁的对象是.class对象,非静态同步方法锁的对象是类的实例,
 * 所以不同线程执行这两个方法可以同时执行
 * */
public class SynchronizedStaticAndNormal8 implements Runnable {

    static SynchronizedStaticAndNormal8 instance= new SynchronizedStaticAndNormal8();

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

    public synchronized static void method1(){
        System.out.println("我是静态加锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束-我是静态加锁的方法");
    }

    public synchronized void method2(){
        System.out.println("我是非静态加锁的方法。我叫"+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束-我是非静态加锁的方法");
    }
}
