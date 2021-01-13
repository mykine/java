//package com.core.reflect;

public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("猫猫吃小鱼");
    }

    @Override
    public void call() {
        System.out.println("喵喵喵～");
    }
}
