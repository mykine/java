package com.thread.singleton;

/**
 * 单例模式实现7：静态内部类-懒加载-线程安全-（可使用）
 * JVM类加载性质保证线程安全
 *
 * */
public class Singleton7 {

    private Singleton7(){

    }

    private static class SingletonInstance{
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
