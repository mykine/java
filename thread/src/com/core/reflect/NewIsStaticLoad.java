package com.core.reflect;

/**
 * new创建对象是静态加载类，在编译时刻就需要加载所有的可能用到的类，如果某一类文件不存在，编译就会报错
 * 静态加载经常会加载一堆实际在业务场景中用不到的类
 *
 * 比如下面的类：
 * 使用编译命令：javac NewIsStaticLoad.java
 * 直接报错:Chicken类不存在
 *
 * 复习命令:
 * 编译命令:javac NewIsStaticLoad.java 将NewIsStaticLoad.java代码文件编译生成NewIsStaticLoad.class字节码文件
 * 运行命令:java NewIsStaticLoad dog 通过java命令并带参数传给main(String args[])方法，运行NewIsStaticLoad.class字节码文件,执行里面的main方法
 * */
public class NewIsStaticLoad {

    public static void main(String[] args) {
        if("dog".equals(args[0])){
            Dog dog = new Dog();
        }else if("cat".equals(args[0])){
            Cat cat = new Cat();
        }else if("chicken".equals(args[0])){
//            Sheep chicken = new Chicken();//只有这个类不存在，通过new静态加载类编译不通过
        }
    }
}
