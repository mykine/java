package com.juc.condition;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用队列+Condition实现生产者消费者模式
 * */
public class ConditionDemo2 {
    static int queueMaxSize = 10;
    static ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(queueMaxSize);
    static ReentrantLock lock = new ReentrantLock();
    static Condition notEmptyCondt = lock.newCondition();
    static Condition notFullCondt = lock.newCondition();

    public static void main(String[] args) {

        //生产者
        for (int i = 0; i < 100; i++) {
            Producer producer = new Producer();
            Thread thread1 = new Thread(producer);
            thread1.start();
        }

        //消费者
        for (int i = 0; i < 100; i++) {
            Comsumer comsumer = new Comsumer();
            Thread thread2 = new Thread(comsumer);
            thread2.start();
        }


    }

    public static String dateNow(){
        Long nowTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateb= new Date(nowTime);
        return sdf.format(dateb)+" ";
    }

}

/**
 * 生产者
 * */
class Producer implements Runnable{

    @Override
    public void run() {
        //生产者算法：
        //1.获取锁
        ConditionDemo2.lock.lock();
        try{
            while (ConditionDemo2.queue.size()>=ConditionDemo2.queueMaxSize){
                //2.判断队列是否满了，是就阻塞(notFullCondt.await())
                try {
                    System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"队列已满，进入阻塞状态,等待消费,size="+ConditionDemo2.queue.size());
                    ConditionDemo2.notFullCondt.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //3.否就往队列里生产,然后唤醒非空条件阻塞的线程(notEmpty.signal())
            try {
                Long msg = System.currentTimeMillis();
                ConditionDemo2.queue.put(msg);
                ConditionDemo2.notEmptyCondt.signalAll();
                System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"生产消息msg="+msg+",并唤醒消费者,size="+ConditionDemo2.queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally{
            //4.解锁
            ConditionDemo2.lock.unlock();
            System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"解锁,size="+ConditionDemo2.queue.size());
        }
    }
}

/**
 * 消费者
 * */
class Comsumer implements Runnable{

    @Override
    public void run() {
        //消费者算法：
        //1.获取锁
        ConditionDemo2.lock.lock();
        try{
            while (ConditionDemo2.queue.size()==0){
                //2.判断队列是否空，是就阻塞(notFullCondt.await())
                try {
                    System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"队列为空，进入阻塞状态,等待数据,size="+ConditionDemo2.queue.size());
                    ConditionDemo2.notEmptyCondt.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //3.否就消费,然后唤醒非满条件阻塞的线程(notFull.signal())
            Long msg = ConditionDemo2.queue.poll();//队列为空时，poll返回null不会阻塞当前线程，take会阻塞当前线程直到队列不为空返回元素
            ConditionDemo2.notFullCondt.signalAll();
            System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"消费消息msg="+msg+",唤醒消费者,size="+ConditionDemo2.queue.size());
        }finally{
            //4.解锁
            ConditionDemo2.lock.unlock();
            System.out.println(ConditionDemo2.dateNow()+Thread.currentThread().getName()+"解锁,size="+ConditionDemo2.queue.size());
        }
    }
}