package com.thread.threadobjectclasscommonmethods;

/**
 * 利用两个线程交替打印100内的奇偶数
 * 使用wait、notify 方式
 * */
public class WaitNotifyPrintOddEvenWait {
    int num = 0;
    Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyPrintOddEvenWait body = new WaitNotifyPrintOddEvenWait();
        //创建2个线程
        OddEvenNumRunnable runnable0 = body.new OddEvenNumRunnable();
        OddEvenNumRunnable runnable1 = body.new OddEvenNumRunnable();
        Thread threadEvenNum = new Thread(runnable0,"偶数");
        Thread.sleep(100);
        Thread threadOddNum = new Thread(runnable1,"奇数");
        //偶数线程打印奇数后,执行monitor对象的notify()唤醒奇数线程，然后执行monitor对象的wait()进入阻塞等待奇数线程来唤醒自己
        threadEvenNum.start();
        Thread.sleep(100);
        //奇数线程打印奇数后,执行monitor对象的notify()唤醒偶数线程，然后执行monitor对象的wait()进入阻塞等待偶数线程来唤醒自己
        threadOddNum.start();
    }

    class OddEvenNumRunnable implements Runnable{

        @Override
        public void run() {
            while(num<=100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+(num++));
                    lock.notify();
                    if(num<=100){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}



