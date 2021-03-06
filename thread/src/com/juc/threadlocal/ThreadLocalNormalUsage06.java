package com.juc.threadlocal;

/**
 * 单个线程内部多个方法共享操作一个参数
 *
 * ThreadLocal实现的原理:
 * Thread类包含一个成员变量ThreadLocalMap变量，类似HashMap数据结构，
 * 当前线程的ThreadLocal对象作为key(是个弱引用),要操作的参数对象作为value(是个强引用),组成键值对Entry存入当前线程的ThreadLocalMap中，以供操作
 *
 * 演示：避免传递参数的麻烦
 * */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process();
    }
}

class User{
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1{
    public void process(){
        User user = new User("阿黄");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2{
    public void process(){
        System.out.println(this.getClass()+"得到用户:"+UserContextHolder.holder.get().getName());
        new Service3().process();
    }
}


class Service3{
    public void process(){
        System.out.println(this.getClass()+"得到用户:"+UserContextHolder.holder.get().getName());
        User user = new User("阿黄2");
        UserContextHolder.holder.set(user);
        new Service4().process();
    }
}


class Service4{
    public void process(){
        System.out.println(this.getClass()+"得到用户:"+UserContextHolder.holder.get().getName());
        UserContextHolder.holder.get().setName("小白");
        new Service5().process();
    }
}

class Service5{
    public void process(){
        System.out.println(this.getClass()+"得到用户:"+UserContextHolder.holder.get().getName());
        //使用完ThreadLocal对象后，要记得执行remove方法，及时删除当前线程的ThreadLocalMap中的Entry对象，避免Entry的value因为强引用无法被GC造成内存泄漏
        UserContextHolder.holder.remove();
    }
}

