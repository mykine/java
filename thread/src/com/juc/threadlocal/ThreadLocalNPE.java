package com.juc.threadlocal;

/**
 * 使用 ThreadLocal 时，写不当的程序造成的空指针异常，跟ThreadLocal本身无关
 * */
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<>();

    public void set(){
        longThreadLocal.set(Thread.currentThread().getId());
    }

//    public long get(){//程序写错成返回基本类型long，拆箱操作时发生空指针异常
    public Long get(){
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
//        threadLocalNPE.set();
        System.out.println(threadLocalNPE.get());
    }
}
