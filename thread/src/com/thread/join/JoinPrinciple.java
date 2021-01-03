package com.thread.join;

import java.util.concurrent.TimeUnit;

/**
 * 通过讲解join原理(Thread类的join是被synchronized修饰的，里面执行了wait()方法,
 * Java的Thread类实例执行完任务后会自动执行notifyAll方法，这是JVM层面的C/C++代码实现的)
 * ，分析出join的替代写法
 * */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"开始执行");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            }
        });
        thread1.start();
        System.out.println(Thread.currentThread().getName()+"开始等待子线程");
//        thread1.join();
        //join的等价代码
        synchronized (thread1){
            thread1.wait();//主线程进入等待后，thread1执行完毕后自动会执行notify()方法，将主线程重新唤醒
        }
        System.out.println(Thread.currentThread().getName()+"子线程执行完毕");
    }
}
