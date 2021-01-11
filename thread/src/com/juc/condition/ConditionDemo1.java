package com.juc.condition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition条件对象，让线程1因不满足条件进入阻塞(使用conditon.await())，让线程2修改条件并唤醒线程1(使用condition.signal())，达到控制多线程执行顺序的逻辑效果
 * JVM是通过condition对象找到因这个condition对象而阻塞的线程来唤醒目标线程的
 * */
public class ConditionDemo1   {
    static int a = 1;
    static ReentrantLock lock = new ReentrantLock();
    static Condition conditon = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo1 demo = new ConditionDemo1();
        Thread thread1 = new Thread(()->ConditionDemo1.method1());
        Thread thread2 = new Thread(()->ConditionDemo1.method2());
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();

    }

    //方法1：因条件阻塞
    public static void  method1(){
        lock.lock();
        try{
            if(a!=0){
                System.out.println(dateNow()+Thread.currentThread().getName()+"条件不满足的情况，进入阻塞状态...");
                try {
                    conditon.await();
                    System.out.println(dateNow()+Thread.currentThread().getName()+"被唤醒，继续执行任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }


    //方法2：改变条件唤醒因条件阻塞的线程
    public static void  method2(){
        lock.lock();
        try{
            a=0;//改变条件,唤醒线程
            System.out.println(dateNow()+Thread.currentThread().getName()+"改变条件,唤醒阻塞的线程");
            conditon.signal();
        }finally {
            lock.unlock();
        }
    }

    public static String dateNow(){
        Long nowTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateb= new Date(nowTime);
        return sdf.format(dateb)+" ";
    }
}
