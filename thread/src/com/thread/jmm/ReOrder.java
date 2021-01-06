package com.thread.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 在达到某种条件下(JVM认为当时运行代码时最优执行指令顺序)，会发生指令重排
 **/
public class ReOrder {
    static int x=0,y=0;
    static int a=0,b=0;

    public static void main(String[] args) throws InterruptedException {
        int cycle=0;
        for(;;){
            x=0;
            y=0;
            a=0;
            b=0;
            cycle++;
            //栅门：使用倒计让多个线程等待到指定时间点同时运行
            CountDownLatch latch = new CountDownLatch(1);

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();//进入Waiting状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a=1;
                    x=b;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();//进入Waiting状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b=1;
                    y=a;
                }
            });

            thread1.start();
            thread2.start();
            System.out.println(thread1.getName()+"的状态="+thread1.getState());
            System.out.println(thread2.getName()+"的状态="+thread2.getState());
            latch.countDown();//打开栅门，唤醒Waiting状态的阻塞线程
            thread1.join();
            thread2.join();
            System.out.println("执行了"+cycle+"次，x="+x+", y="+y);
            if(0==x && 0==y){
                //发生指令重排时会造成x和y可能均等于0的特殊情况
                break;
            }
        }


    }

}
