package com.juc.immutable;

/**
 * final修饰的类，不能被继承
 * final修饰一个类的方法后，这个方法不能被重写
 * */
public class FinalFunc {

    public void drink(){
        System.out.println("父类喝酒");
    }

    public final void eat(){
        System.out.println("父类吃肉");
    }

    public static void sleep(){
        System.out.println("父类睡觉");
    }

}

class SunFinal extends FinalFunc{
    @Override
    public void drink() {
        System.out.println("子类喝奶");
    }

    //无法重写父类final修饰的方法
//    @Override
//    public void eat() {
//        System.out.println("子类喝奶");
//    }

//    @Override
    public static void sleep() {
        System.out.println("静态方法不能重写，这是子类自己的静态方法");
    }
}

/**
 * String类是final修饰的，无法被继承
 * */
//class SunFinal2 extends String{
//
//}