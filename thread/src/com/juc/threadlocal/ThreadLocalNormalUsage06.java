package com.juc.threadlocal;

/**
 * 单个线程内部多个方法共享操作一个参数
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
    }
}

