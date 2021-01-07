package com.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 加锁解决线程安全问题
 * 线程池执行1000个打印日期的任务
 * 每个任务都会new一个功能属性相同的SimpleDateFormat对象并销毁它，浪费内存和cpu，可以优化这块代码，
 * 使用静态的SimpleDateFormat对象，JVM类加载时就初始化，并被多线程共享使用
 * 但是造成了线程安全问题，出现多个线程打印的值一模一样的情况
 *
 * */
public class ThreadLocalNormalUsage04 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int a = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadLocalNormalUsage04().date(a));
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds){

        Long millseconds = seconds*1000L;
        //参数单位是毫秒,从1970.1.1 00:00:00 GMT计时(对应我国东八区的是1970.1.1 08:00:00)
        Date d = new Date(millseconds);
        //每个任务都会new一个功能属性相同的SimpleDateFormat对象并销毁它，浪费内存和cpu，可以优化这块代码，使用静态的SimpleDateFormat对象，JVM类加载时就初始化，并被多线程共享使用
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String s = null;
        synchronized (ThreadLocalNormalUsage04.class){
            s = dateFormat.format(d);
        }
        return s;
    }
}
