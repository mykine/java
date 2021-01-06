package com.thread.singleton;

/**
 * 单例模式实现5：懒汉式（同步加锁优化，缩短加锁范围）-线程不安全-（不推荐）
 * 缺点：为提高效率，同步加锁缩小范围到实例化代码块，但多个线程都能同时进入到实例化代码块门口，就算加锁，
 * 一个线程实例化执行完毕后释放锁，另一个线程能及时获取锁重复执行实例化代码，造成线程不安全
 * */
public class Singleton5 {

    private  static Singleton5 instance ;


    private Singleton5(){

    }

    public  static Singleton5 getInstance(){
        if(instance == null){
            synchronized (Singleton5.class){
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
