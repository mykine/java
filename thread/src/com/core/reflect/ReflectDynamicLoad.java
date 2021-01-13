//package com.core.reflect;//手动在文件目录下执行javac、java命令时不要设置包路径，否则编译会报"找不到符号"错误，运行时会报"找不到或无法加载主类"的错误

/**
 * 通过反射机制动态加载类，在运行阶段按需加载用到的类
 * 进入文件目录，执行编译和运行命令
 * javac ReflectDynamicLoad.java 编译成功，生成字节码文件ReflectDynamicLoad.class
 * javac Dog.java 编译此时动态要使用的类(Dog实现的接口Animal也会被编译)
 * java ReflectDynamicLoad Dog 使用java命令并带参数传入main函数，动态加载Dog类并实例化使用
 *
 * javac Cat.java
 * java ReflectDynamicLoad Cat
 * */
public class ReflectDynamicLoad {

    public static void main(String[] args) {
        try {
            //通过范型动态加载类，这些类都是继承了统一接口的类
            Class<?> c = Class.forName(args[0]);
//            System.out.println(c.getSimpleName());
            try {
                Animal animal = (Animal)c.newInstance();
                animal.eat();
                animal.call();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
