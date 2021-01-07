package com.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示 创建线程池-Executors.newFixedThreadPool方法
 * */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }
    }

   static class Task implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"执行任务..");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
