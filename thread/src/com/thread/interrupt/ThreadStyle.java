package com.thread.interrupt;

public class ThreadStyle extends Thread {
    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("继承Thead类实现线程");
    }
}
