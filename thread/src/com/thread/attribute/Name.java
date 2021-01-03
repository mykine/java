package com.thread.attribute;

public class Name {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程是否是守护线程："+Thread.currentThread().isDaemon());
        System.out.println("子线程是否是守护线程："+thread.isDaemon());
    }
}
