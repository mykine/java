package com.thread.threadobjectclasscommonmethods;
/**
 * 3个线程,线程1和2首先被阻塞，线程3唤醒他们，分别用notify、notifyAll唤方式醒
 * stat先执行不代表先启动
 * */
public class WaitNotifyAll implements Runnable {

    public static Object object = new Object();

    @Override
    public void run() {
        synchronized (object){
            System.out.println(Thread.currentThread().getName()+"got lock");
            try {
                System.out.println(Thread.currentThread().getName()+"wait to restart");
                object.wait();
                System.out.println(Thread.currentThread().getName()+"restart to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll runnable = new WaitNotifyAll();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    object.notifyAll();
                    System.out.println(Thread.currentThread().getName()+"notified all ");
                }
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(200);
        thread3.start();
    }
}
