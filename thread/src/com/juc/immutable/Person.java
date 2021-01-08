package com.juc.immutable;

/**
 * final关键字修饰对象的成员，不可变的对象，演示其他类无法修改这个对象
 * 不可变的对象，对于多线程操作来说是线程安全的，因为它的成员不能被修改
 * */
public class Person {
    public static void main(String[] args) {
        Person p = new Person();
//        p.age = 10;//无法赋值，因为是不可变的
    }
    final String name = "Tom";
    final int age = 18;
}
