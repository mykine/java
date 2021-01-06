package com.thread.singleton;

/**
 * 单例模式实现3：懒汉式（懒加载）-线程不安全-（不可用）
 * 缺点：懒加载实例化对象，多线程执行时，非原子操作、不可见性造成线程不安全,可能出现多个实例
 * */
public class Singleton3 {

    private  static Singleton3 instance ;


    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }
}
