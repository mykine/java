package com.juc.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 场景2：利用CountDownLatch实现多个线程等待一个线程倒计时结束后统一开始执行任务,
 * 类似跑步比赛，多个运动员再起跑线上一起等待同一个裁判员倒计时发令枪开始
 * */
public class CountDownLatchDemo2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
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
                        latch.await();//等待倒计时，统一被唤醒同时开始执行任务
                        System.out.println(dateNow()+Thread.currentThread().getName()+"开跑...");
                        Thread.sleep((long)Math.random()*10*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            pools.submit(r);
        }

        try {
            Thread.sleep(1000);
            System.out.println(dateNow()+Thread.currentThread().getName()+"预备..");
            System.out.println(dateNow()+Thread.currentThread().getName()+"开始!");
            latch.countDown();

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

