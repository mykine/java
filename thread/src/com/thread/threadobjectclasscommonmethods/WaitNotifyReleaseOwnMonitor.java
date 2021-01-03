package com.thread.threadobjectclasscommonmethods;
/**
 * 证明wait只释放当前的那把锁
 * */
public class WaitNotifyReleaseOwnMonitor {

    public static Object object1 = new Object();
    public static Object object2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName()+"获取到lock1");
                    synchronized (object2){
                        System.out.println(Thread.currentThread().getName()+"获取到lock2");
                        try {
                            System.out.println(Thread.currentThread().getName()+"释放lock1");
                            object1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"运行完释放lock2");
                    }
                    System.out.println(Thread.currentThread().getName()+"运行完释放lock1");
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object1){
                    System.out.println(Thread.currentThread().getName()+"获取到lock1");
                    System.out.println(Thread.currentThread().getName()+"尝试获取lock2");
                    synchronized (object2){
                        System.out.println(Thread.currentThread().getName()+"获取到lock2");
                    }
                    System.out.println(Thread.currentThread().getName()+"运行完释放lock1");
                }
            }
        });
        thread1.start();
        Thread.sleep(100);
        thread2.start();

    }
}
