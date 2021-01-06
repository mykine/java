package com.thread.singleton;

/**
 * 单例模式实现1：饿汉式（静态常量）-线程安全-（可用）
 * 缺点：JVM类加载时就会实例化static修饰的单例对象，过早的占用内存，如果业务中没有用到单例对象，就会造成内存浪费
 * */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
