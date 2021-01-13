package com.core.reflect;

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("狗狗吃骨头");
    }

    @Override
    public void call() {
        System.out.println("汪汪汪~");
    }
}
