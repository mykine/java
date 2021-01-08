package com.juc.lock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class RecursionDemo {
    static ReentrantLock lock = new ReentrantLock();

    private static void accessResource(){
        lock.lock();
        try{
            System.out.println(new Date()+"获得锁了,次数="+lock.getHoldCount());
            if(lock.getHoldCount()<5){
                accessResource();
            }
        }finally{
            lock.unlock();
            System.out.println(new Date()+"解锁了,次数="+lock.getHoldCount());
        }
    }

    public static void main(String[] args) {
        accessResource();
    }
}
