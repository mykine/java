package com.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用ThreadLocal实现每个线程自己独享的SimpleDateFormat对象，保证线程安全性，
 * 这样有多少个线程就有多少个SimpleDateFormat对象，相比绑定任务数量来说，少得多
 * 线程池执行1000个打印日期的任务
 *
 * */
public class ThreadLocalNormalUsage05 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int a = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(new ThreadLocalNormalUsage02().date(a));
                }
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds){

        Long millseconds = seconds*1000L;
        //参数单位是毫秒,从1970.1.1 00:00:00 GMT计时(对应我国东八区的是1970.1.1 08:00:00)
        Date d = new Date(millseconds);

        //缺点:每个任务都会创建和销毁一个SimpleDateFormat对象，如果有1000个任务，就会很消耗内存和CPU,性能低下
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        //使用ThreadLocal获取当前执行任务的线程自己独享的SimpleDateFormat对象
//        SimpleDateFormat dateFormat = ThreadSafeFormat.dateFormatThreadLocal.get();//这个get方法会返回ThreadLocal的initialValue方法返回的对象
        SimpleDateFormat dateFormat = ThreadSafeFormat.dateFormatThreadLocal2.get();//这个get方法会返回ThreadLocal的initialValue方法返回的对象
        return dateFormat.format(d);
    }
}

class ThreadSafeFormat{

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

    //lamboda表达式写法
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 = ThreadLocal.withInitial(
            ()->new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    );

}