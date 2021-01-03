package com.thread.javasynchronized;

/**
 *
 * 可重入粒度是线程:同一个线程下获取的锁可重入
 * 测试：递归调用本方法
 * */
public class SynchronizedRecursion10 {
    int a=0;

    public static void main(String[] args) {
        SynchronizedRecursion10 obj = new SynchronizedRecursion10();
        obj.method1();
    }

    private synchronized void method1(){
        System.out.println("这是method1,a="+a);
        if(0==a){
            a++;
            method1();
        }
    }
}
