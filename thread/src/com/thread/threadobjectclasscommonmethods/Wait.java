package com.thread.threadobjectclasscommonmethods;
/**
 * 展示 wait和notify的基本用法
 * 1.研究代码执行顺序
 * 2.证明wit是释放锁的
 * */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("我是线程"+Thread.currentThread().getName()+"执行wait");
                try {
                    object.wait();//等待会释放出占用的monitor锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"重新获取到锁");
            }

        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println("我是线程"+Thread.currentThread().getName()+"执行notify");
                object.notify();//随机唤醒占用object对象monitor锁的线程
                System.out.println(Thread.currentThread().getName()+"执行完毕准备释放锁");
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 =new Thread1();
        Thread2 t2 =new Thread2();
        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
