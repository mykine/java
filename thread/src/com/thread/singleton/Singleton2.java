package com.thread.singleton;

/**
 * 单例模式实现2：饿汉式（静态代码块）-线程安全-（可用）
 * 缺点：JVM类加载时就会执行static修饰的代码块中单例对象实例化，过早的占用内存，如果业务中没有用到单例对象，就会造成内存浪费
 * */
public class Singleton2 {

    private final static Singleton2 INSTANCE ;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        return INSTANCE;
    }
}
