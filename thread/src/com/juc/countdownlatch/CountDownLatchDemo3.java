package com.juc.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 场景3：利用两个CountDownLatch实现多个等一和一个等多个同时存在的场景
 * 类似跑步比赛，多个运动员在起跑线上一起等待同一个裁判员倒计时发令枪开始,然后裁判员要等所有运动员都跑完才宣布比赛结束
 * */
public class CountDownLatchDemo3 {

    public static void main(String[] args) {
        CountDownLatch beginLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(5);
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
                        beginLatch.await();//等待倒计时，统一被唤醒同时开始执行任务
                        System.out.println(dateNow()+Thread.currentThread().getName()+"开跑...");
                        Thread.sleep((long)Math.random()*10*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        System.out.println(dateNow()+Thread.currentThread().getName()+"到终点了");
                        endLatch.countDown();
                    }
                }
            };
            pools.submit(r);
        }

        try {
            Thread.sleep(1000);
            System.out.println(dateNow()+Thread.currentThread().getName()+"预备..");
            System.out.println(dateNow()+Thread.currentThread().getName()+"开始!");
            beginLatch.countDown();

            System.out.println(dateNow()+Thread.currentThread().getName()+"正在等待所有人都跑完..");
            endLatch.await();
            System.out.println(dateNow()+Thread.currentThread().getName()+"比赛结束!");
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

