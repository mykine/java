package com.juc.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier:可轮回执行的线程栅栏工具类,
 * CyclicBarrier和CountDownLatch的区别:
 * 1.CyclicBarrier是等固定数量的线程都达到了栅栏位置(执行await()方法的代码位置)才能继续执行，而CountDownLatch只需等待数字到0，
 * 也就是说,CyclicBarrier用线程作为计数达到触发条件，CountDownLatch用(执行countDown())事件作为计数触发条件
 * 2.CountDownLatch倒数到0并触发打开栅栏后就不能再次使用了，除非新建实例,而CyclicBarrier可以重复使用
 * */
public class CyclicBarrierDemo {

    public static String dateNow(){
        Long nowTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateb= new Date(nowTime);
        return sdf.format(dateb)+" ";
    }

    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println(dateNow()+Thread.currentThread().getName()+"达到人数，发车一波~");
            }
        }) ;

        for (int i = 0; i < 10; i++) {
            new Thread(new ByBus(i,cyclicBarrier)).start();
        }
    }

    /**
     * 乘公交车
     * */
    static class ByBus implements Runnable{
        int number;
        CyclicBarrier  cyclicBarrier;

        public ByBus(int number, CyclicBarrier cyclicBarrier) {
            this.number = number;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(dateNow()+Thread.currentThread().getName()+"number="+number+"前往公交车");
            try {
                Thread.sleep(200);
                System.out.println(dateNow()+Thread.currentThread().getName()+"number="+number+"上公交车了");
                try {
                    cyclicBarrier.await();//进入阻塞状态，等待其他线程一起凑数达到栅栏放开条件,被唤醒再继续执行下面的代码
                    System.out.println(dateNow()+Thread.currentThread().getName()+"number="+number+"在公交车上出发了，开心~");
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
