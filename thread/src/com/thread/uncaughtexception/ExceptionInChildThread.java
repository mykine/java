package com.thread.uncaughtexception;

/**
 * 单线程主线程抛出异常可以利用try-catch进行捕获处理
 * 多线程，子线程发生异常，主线程无法利用try-catch捕获子线程的异常
 * */
public class ExceptionInChildThread implements Runnable {

    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i <300 ; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
