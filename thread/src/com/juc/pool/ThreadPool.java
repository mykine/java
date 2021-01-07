package com.juc.pool;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 演示 创建线程池执行对象的关键参数,最后要记得关闭线程池
 * 当任务队列满了而且工作线程数已经达到设置的最大线程数时,新来任务拒绝策略,一般有4种:
 * 1、ThreadPoolExecutor.AbortPolicy：丢弃任务并抛出RejectedExecutionException异常（这是默认采用的策略，一般通过捕捉该异常来处理额外的任务）
 * 2、ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
 * 3、ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
 * 4、ThreadPoolExecutor.CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
 * */
public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //创建线程的工厂
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();//默认的
//        ThreadFactory threadFactory = new MyThreadFactory();//自定义
        ThreadFactory threadFactory = new MyThreadFactory2("自定义任务");//自定义2

        //这里的拒绝策略是让调用线程(例如主线程)执行新任务
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                2,//核心线程数目
                4,//最大线程数目
                0,//多余的空闲线程存活时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(5),//任务队列,任务满了时会新建线程达到最大线程数
                threadFactory,//创建线程的工厂
                rejectedExecutionHandler//(当任务队列满了而且工作线程数已经达到设置的最大线程数时)新来任务拒绝策略,一般有4种
                );
        for (int i = 0; i < 1000; i++) {
            Task task = new Task("任务"+(i+1));
            executorService.execute(task);
            System.out.println(new Task("任务"+(i+1)).toString()+"-code="+task.hashCode());
        }
        System.out.println((new Date())+" "+"线程池是否开始停止:shutdown="+executorService.isShutdown());
        System.out.println((new Date())+" "+"线程池是否已经停止:shutdown="+executorService.isTerminated());
        Thread.sleep(1500);

        //没有新任务了，开始关闭线程池,此后线程池会执行完当前所有已接受的任务，不再接受新的任务
        executorService.shutdown();

        System.out.println((new Date())+" "+"线程池是否开始停止:shutdown="+executorService.isShutdown());
        System.out.println((new Date())+" "+"线程池是否已经停止:shutdown="+executorService.isTerminated());
        executorService.execute(new Task("任务0"));//添加新任务无效
        System.out.println((new Date())+" "+"线程池是否开始停止:shutdown="+executorService.isShutdown());
        System.out.println((new Date())+" "+"线程池是否已经停止:shutdown="+executorService.isTerminated());

    }


    /**
     * 自定义创建线程工厂类
     * */
    static class MyThreadFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
//            String threadName = "myThreadPool-"+r.hashCode()+"-"+r.toString();//主线程中的
            String threadName = "myThreadPool-"+r.hashCode();
            System.out.println("自定义线程类创建线程r-"+threadName);
            return new Thread(r,"myThreadPool-"+threadName);
        }

    }

    /**
     * 自定义创建线程工厂类2,模拟DefaultThreadFactory通过明明前缀拼接序号给线程命名
     * */
    static class MyThreadFactory2 implements ThreadFactory{

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory2(String name) {

            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            if (null == name || name.isEmpty()) {
                name = "pool";
            }

            namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }

    }

   static class Task implements Runnable{

        private String name;

       public Task(String name) {
           this.name = name;
       }

       @Override
       public String toString() {
           return this.name;
       }

       @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"执行任务..");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void work(String s){
        System.out.println(Thread.currentThread().getName()+"执行:"+s);
    }
}
