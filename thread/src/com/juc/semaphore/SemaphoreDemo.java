package com.juc.semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 信号量:类似许可证，只有拿到许可证的线程才能干活，否则阻塞等待
 * */
public class SemaphoreDemo {
    public static String dateNow(){
        Long nowTime = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateb= new Date(nowTime);
        return sdf.format(dateb)+" ";
    }

    //3个许可证的信号量对象,采用公平策略重新分配
    static Semaphore semaphore = new Semaphore(3,true);

    static AtomicInteger flag = new AtomicInteger();

    static CountDownLatch latch = new CountDownLatch(100);//对应100个任务，每个任务都减1个latch

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                50,
                50,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        for (int i = 0; i < 100; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(1);//获取信号量资源
                        try{
                            System.out.println(dateNow()+Thread.currentThread().getName()+"得到信号量，开始干活");
                            Thread.sleep(10);
                        }finally {
                            int f = flag.incrementAndGet();
                            System.out.println(dateNow()+Thread.currentThread().getName()+"干活结束"+f+"，释放信号量");
                            semaphore.release(1);//获取多少个信号量资源，用完后就要释放掉多少个
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        latch.countDown();
                    }
                }
            };
            pool.submit(r);
        }

        System.out.println(dateNow()+Thread.currentThread().getName()+"开始监管...");
        try {
            latch.await();
            System.out.println(dateNow()+Thread.currentThread().getName()+"监管结束,flag="+flag.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

    }



}
