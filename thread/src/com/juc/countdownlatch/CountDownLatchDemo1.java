package com.juc.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 场景1：利用CountDownLatch实现一个线程等待多个线程都结束再继续执行任务
 * */
public class CountDownLatchDemo1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ThreadPoolExecutor pools = new ThreadPoolExecutor(
                5,
                5,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        for (int i = 0; i < 5; i++) {

            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(dateNow()+Thread.currentThread().getName()+"执行完任务...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                    }
                }
            };
            pools.submit(r);
        }

        System.out.println(dateNow()+Thread.currentThread().getName()+"正在等待5个线程都执行完毕..");
        try {
            latch.await();//当前线程进入阻塞，等待CountDownLatch倒计时完毕后被唤醒执行下面的代码
            System.out.println(dateNow()+Thread.currentThread().getName()+"等到了5个线程都执行完毕,被唤醒了，继续操作!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pools.shutdown();
        }

    }

    public static String dateNow(){
        Long nowTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateb= new Date(nowTime);
        return sdf.format(dateb)+" ";
    }

}

