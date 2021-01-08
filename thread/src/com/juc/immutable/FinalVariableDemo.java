package com.juc.immutable;

/**
 * final变量的赋值时机
 * */
public class FinalVariableDemo {
    private final int a = 0;//在声明时赋值

    private final String s;//在构造函数中赋值
    public FinalVariableDemo(String s) {
        this.s = s;
    }

    private final Long b;
    //在构造代码块中赋值
    {
        b = 0L;
    }

    private final static int c = 1;//static变量声明时赋值
    private final static int d;
    //static变量在静态代码块赋值
    static {
        d = 2;
    }

    void testFinal(){
        final int b;
        b=22;//使用前赋值
        int c = b;
    }

}
