package com.thread.javasynchronized;

/**
 * 对象锁示例1，代码块锁形式
 * */
public class SynchronizedObjectCodeBlock2 implements Runnable {

    static SynchronizedObjectCodeBlock2 instance= new SynchronizedObjectCodeBlock2();
    Object lock1=new Object();
    Object lock2=new Object();

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
        synchronized(this)
        {
            System.out.println("我是对象锁的代码块形式。我叫"+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行结束");
        }
    }

//    @Override
//    public void run() {
//        synchronized(lock1)
//        {
//            System.out.println("Lock1-我是对象锁的代码块形式。我叫"+Thread.currentThread().getName());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"Lock1-运行结束");
//        }
//
//        synchronized(lock2)
//        {
//            System.out.println("Lock2-我是对象锁的代码块形式。我叫"+Thread.currentThread().getName());
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"Lock2-运行结束");
//        }
//    }
}
