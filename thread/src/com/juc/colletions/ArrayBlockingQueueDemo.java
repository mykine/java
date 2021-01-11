package com.juc.colletions;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {


    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
        ProducerJob r1 = new ProducerJob(queue);
        Consumer r2 = new Consumer(queue);

        Thread thread1 = new Thread(r1,"生产者线程0");
        Thread thread2 = new Thread(r2,"消费者线程1");
        thread1.start();
        thread2.start();

    }
}

class ProducerJob implements Runnable{

    private BlockingQueue<String> queue;

    public ProducerJob(BlockingQueue<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" "+"10个候选人来了");
        for (int i = 0; i < 10; i++) {
            try {
                String person = "候选人"+i;
                queue.put(person);
                System.out.println(Thread.currentThread().getName()+" "+"接待了"+person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            queue.put("stop");//标记结束的消息
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Consumer implements Runnable{

    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            String taker = queue.take();
            while(!(taker = queue.take()).equals("stop")){
                System.out.println(Thread.currentThread().getName()+" "+taker+"消费完毕,queue.size="+queue.size());
            }
            System.out.println(Thread.currentThread().getName()+" "+taker+"所有人都消费完毕,queue.size="+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
