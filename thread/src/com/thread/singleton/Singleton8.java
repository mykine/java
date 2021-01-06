package com.thread.singleton;

/**
 * 单例模式实现8：枚举(最佳推荐)
 * 反编译可以发现枚举本质就是final class，继承了枚举父类，父类的实例都是static修饰的，所以枚举本质就是静态类
 * 线程安全-可避免反系列化
 * */
public enum Singleton8 {

    INSTANCE;

    public void func1(){

    }
}
