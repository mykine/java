package com.thread.javasynchronized;

/**
 *
 * 可重入粒度是线程:同一个线程下获取的锁可重入
 * 测试：调用父类的方法
 * */
public class SynchronizedSuperClass12 {

    public synchronized void doSomeThing(){
        System.out.println("我是父类方法");
    }
}

class Son extends SynchronizedSuperClass12{
    @Override
    public synchronized void doSomeThing() {
        System.out.println("我是子类方法");
        super.doSomeThing();
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.doSomeThing();
    }
}
