package com.core.reflect;

public class Sheep implements Animal {
    @Override
    public void eat() {
        System.out.println("小羊吃小草");
    }

    @Override
    public void call() {
        System.out.println("咩咩咩~");
    }
}
