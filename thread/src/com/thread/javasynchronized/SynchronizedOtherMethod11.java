package com.thread.javasynchronized;

/**
 *
 * 可重入粒度是线程:同一个线程下获取的锁可重入
 * 测试：调用类内部其他方法
 * */
public class SynchronizedOtherMethod11 {
    int a=0;

    public static void main(String[] args) {
        SynchronizedOtherMethod11 obj = new SynchronizedOtherMethod11();
        obj.method1();
    }

    private synchronized void method1(){
        System.out.println("这是method1,a="+a);
        a++;
        method2();
    }
    private synchronized void method2(){
        System.out.println("这是method2,a="+a);
        a++;
    }
}
