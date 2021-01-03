package com.thread.javasynchronized;

/**
 * 不用并发手段的后果，不能保证并发一致性，多线程不安全，数据是无法如预期进行读写操作，造成脏数据
 * */
public class DisappearedRequest1 implements Runnable {

    static DisappearedRequest1 instance= new DisappearedRequest1();

    static int num=0;
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("num="+num);
    }


//    @Override
//    public void run() {
//        for(int i=1;i<=50000;i++){
//            num++;
//        }
//    }

    @Override
    public void run() {
        synchronized (DisappearedRequest1.class){
            for(int i=1;i<=50000;i++){
                num++;
            }
        }
    }
}
