package com.thread.singleton;

/**
 * 单例模式实现4：懒汉式（同步加锁）-线程安全-（不推荐）
 * 缺点：同步加锁获取对象方法，效率低下
 * */
public class Singleton4 {

    private  static Singleton4 instance ;


    private Singleton4(){

    }

    public synchronized static Singleton4 getInstance(){
        if(instance == null){
            instance = new Singleton4();
        }
        return instance;
    }
}
