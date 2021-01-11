package com.juc.colletions;

import java.util.concurrent.ConcurrentHashMap;

/**
 * CuncerrentHashMap 只能保证自己put()方式是同步锁家线程安全的，
 * 如果put()和其他操作组合，并不能保证是原子操作，也就不一定是线程安全的
 * */
public class OptionsNotSafe implements  Runnable{
    static ConcurrentHashMap<String,Integer> scores = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        scores.put("tom",0);
        Thread thread1 = new Thread(new OptionsNotSafe());
        Thread thread2 = new Thread(new OptionsNotSafe());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(scores);

    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            //这三行代码不能保证原子性，线程不安全
//            Integer oldScore  = scores.get("tom");
//            Integer newScore = oldScore+1;
//            scores.put("tom",newScore);

            //改用循环执行replace(,,)方法(本质是CAS乐观锁操作)，直到返回true停止，线程安全
            while (true){
                Integer oldScore  = scores.get("tom");
                Integer newScore = oldScore+1;
                if(scores.replace("tom",oldScore,newScore)){
                   break;
                }
            }
        }
    }
}
