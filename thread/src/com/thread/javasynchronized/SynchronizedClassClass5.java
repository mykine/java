package com.thread.javasynchronized;

/**
 * 类锁的本质是对同一个类的N个实例共用唯一的.class对象加锁,(java中的对象分为实例对象和Class对象)
 * 类锁示例2，多个线程使用不同的runnable对象时，(静态)方法锁形式
 * */
public class SynchronizedClassClass5 implements Runnable {

    static SynchronizedClassClass5 instance1= new SynchronizedClassClass5();
    static SynchronizedClassClass5 instance2= new SynchronizedClassClass5();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()){

        }
        System.out.println("Finished");
    }


    @Override
    public void run() {
        method();
    }

    public  void method(){

        synchronized (SynchronizedClassClass5.class){
//        synchronized (this.getClass()){
            System.out.println("我是类锁的第二种形式:synchronized(*.class)形式。我叫"+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"运行结束");
        }
    }
}
