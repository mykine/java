package com.thread.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * 通过wait()、notify()实现生产者消费者模式
 * */
public class ProducerConsumerModel {

    public static void main(String[] args) throws InterruptedException {
        //消息队列
        MessageQueue queue = new MessageQueue();
        //生产者
        Thread procuder = new Thread(new Producer(queue));
        //消费者
        Thread consumer = new Thread(new Consumer(queue));

        procuder.start();
        Thread.sleep(100);
        consumer.start();

    }

}

class Producer implements Runnable{

    private  MessageQueue queue;

    public Producer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            queue.put(new Date());
        }
    }
}

class Consumer implements Runnable{

    private  MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            queue.take();
        }
    }
}

class MessageQueue{
    private int maxSize;
    private LinkedList<Date> list;


    public MessageQueue() {
        this.maxSize = 10;
        list = new LinkedList<Date>();
    }

    //  生产消息
    public synchronized void put(Date date){
        while (this.list.size()>=this.maxSize){
            try {
                System.out.println(Thread.currentThread().getName()+"消息数已满"+list.size()+"进入阻塞状态,暂停生产");
                this.wait();//队列满了就阻塞生产
                System.out.println(Thread.currentThread().getName()+"被唤醒，继续生产消息,现有消息数"+list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(date);
        System.out.println(Thread.currentThread().getName()+"队列中有"+this.list.size()+"个消息");
        this.notify();//唤醒可能阻塞的消费者消费
    }

    //消费消息
    public synchronized void take(){
        while(this.list.size()<=0){
            try {
                System.out.println(Thread.currentThread().getName()+"消息数为空"+list.size()+"进入阻塞状态,暂停消费");
                this.wait();//队列为空就阻塞消费
                System.out.println(Thread.currentThread().getName()+"被唤醒，继续消费消息,现有消息数"+list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"消费了1个消息"+list.poll()+"队列中还有"+this.list.size()+"个消息");
        this.notify();//唤醒可能阻塞的生产者进重新进行消息生产
    }
}
