package com.juc.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示：公平锁和不公平锁,java锁默认不公平，解锁时，新来的线程优先阻塞队列中待唤醒线程，获取锁，
 * 优点：节省唤醒等待状态的线程的开销，提高了性能
 * 缺点: 可能造成饥饿现象,即：某些待等待线程一直被新线程抢锁，没有机会被唤醒得到锁
 * */
public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threadList = new Thread[10];
        for (int i = 0; i < threadList.length; i++) {
            threadList[i] = new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" 开始打印");
                printQueue.printJob();
                System.out.println(Thread.currentThread().getName()+" 打印完毕");
            });
        }

        //公平的情况下，每个线程执行2个任务期间，会按顺序唤醒阻塞队列中的线程得到释放的锁进行执行，也就是可能出现打印的线程信息不是一组组完整的
        for (int i = 0; i < threadList.length; i++) {
            try {
                threadList[i].start();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class PrintQueue{

    private Lock queueLock = new ReentrantLock(false);//构造函数参数boolean fair,是否公平,默认false
//    private Lock queueLock = new ReentrantLock(true);//构造函数参数boolean fair,是否公平,默认false

    public void printJob(){
        for (int i = 0; i < 2; i++) {
            queueLock.lock();
            try {
                execJob(i+1);
            }finally {
                queueLock.unlock();
            }
        }
    }

    private void execJob(int taskNum){
        int random = new Random().nextInt(2)+1;
        System.out.println(Thread.currentThread().getName()+"正在打印任务"+taskNum+"，预计耗时"+random+"秒");
        try {
            Thread.sleep(random*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
