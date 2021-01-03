package com.thread.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Volatile的局限性，陷入长时间阻塞时，volatile是无法用于停止线程的
 * 例如：生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 * */
public class ErrorWayVolatileCantStopThread {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread thread =new Thread(producer);
        thread.start();
        Thread.sleep(1000);
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        //让生产者停止下来，但实际情况是生产者处于阻塞状态后生产者线程run代码中判断canceled的代码根本不会执行，也就无法知道自己需要停止下来，
        // 所以要通过其他协作发interrupt信号的方式通知阻塞的线程才能达到正确停止线程的目的
        producer.canceled=true;
        System.out.println(producer.canceled);
    }
}

class Producer implements  Runnable{
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num =0;
        try{
            while( num <= 10000){
                num++;
                if(canceled){
                    System.out.println(Thread.currentThread().getName()+":"+num+"，取消执行了");
                    break;
                }
                if( num % 100 ==0 ){
                    storage.put(num);
                    System.out.println(Thread.currentThread().getName()+":"+num+"是100的倍数,放入队列中了");
                }
            }
        }catch (Exception e){
            System.out.println(Thread.currentThread().getName()+"生产者发生异常了!");
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"生产者操作结束!");
        }
    }
}

class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums(){
        if(Math.random()>0.95){
            return false;
        }
        return true;
    }

}