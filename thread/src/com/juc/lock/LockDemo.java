package com.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对线程打印字符串，如何保证线程安全性
 *
 * */
public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }

    private void init(){
        final Outputer outputer  = new Outputer();
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(5);
                    outputer.output("我爱北京天安门");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(5);
                    outputer.output("一二三四五六七");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static class Outputer{
        Lock lock = new ReentrantLock();

        public void output(String  name){
            lock.lock();
            try{
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
            }finally {
                lock.unlock();
                System.out.println("\r\n");
            }
        }

    }

}
