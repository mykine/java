package com.thread.attribute;

public class Id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程ID"+Thread.currentThread().getId());
        System.out.println("子线程ID"+thread.getId());
    }
}
