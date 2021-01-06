package com.thread.singleton;

/**
 * 单例模式实现6：双重检查-延迟加载-线程安全-（推荐）
 * 优点：用volatile保证可见性并禁止指令重排，synchronized同步代码内部多个判断，杜绝重复实例化
 * 新建对象包含3个指令步骤:1创建新的空对象，2执行构造方法，3将实例赋值到引用
 * 若没有volatile修饰变量，可能出现指令重排，步骤3先于步骤2执行，在步骤1、3刚执行完时被实例被使用时可能造成空指针异常
 * */
public class Singleton6 {

    private volatile static Singleton6 instance ;


    private Singleton6(){

    }

    public  static Singleton6 getInstance(){
        if(instance == null){
            synchronized (Singleton6.class){
                if(instance == null){
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
