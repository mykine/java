package com.juc.immutable;

/**
 * 代码块的作用和执行顺序
 * 执行顺序是：
     Father静态代码块,a=1
     子类静态代码块,a=1
     Father构造代码块,a=1
     Father构造函数
     子类构造代码块,b=2
     子类构造函数,c=3
     ------------------------------
     Father构造代码块,a=1
     Father构造函数
     子类构造代码块,b=2
     子类构造函数,c=9
 * 原因：实例化子类时，子类会现调用隐藏的super方法即父类的构造方法，
 * 静态代码块会在JVM类加载时就执行仅有的一次，
 * 构造代码块在编译阶段被移到构造方法里首行
 *
 * * 注意：一个类的静态代码块只会执行一次
 *        子类的构造函数会执行隐藏的super即父类的构造函数
 *        无论调用哪个构造函数，构造代码块都会执行，而且相当于构造函数第一行执行
 *
 * */
public class StaticFunc extends Father {

    private static int a=1 ;
    private int b = 2;
    private int c=3;

    {
        System.out.println("子类构造代码块,b="+b);
    }

    public StaticFunc() {
        System.out.println("子类构造函数,c="+c);
    }

    public StaticFunc(int c) {
        this.c = c;
        System.out.println("子类构造函数,c="+c);
    }

    static{
        System.out.println("子类静态代码块,a="+a);
    }

}


 class Father {

    private static int a=1 ;

    {
        System.out.println("Father构造代码块,a="+a);
    }

    public Father() {
        System.out.println("Father构造函数");
    }

    static{
        System.out.println("Father静态代码块,a="+a);
    }

}

class FuncTest{

    public static void main(String[] args) {
        /**
         * 注意：一个类的静态代码块只会执行一次
         *      子类的构造函数会执行隐藏的super即父类的构造函数
         *      无论调用哪个构造函数，构造代码块都会执行，而且相当于构造函数第一行执行
         * */
        StaticFunc staticFunc = new StaticFunc();
        System.out.println("------------------------------");
        StaticFunc staticFunc2 = new StaticFunc(9);
    }

}
