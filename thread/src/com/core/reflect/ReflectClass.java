package com.core.reflect;
import java.lang.Class;
/**
 * 反射原理: JAVA中有个类叫做java.lang.Class ，java中一切皆对象，即类型(int、String、bool、各种类等等)、类里面修饰用的关键字等等都是java.lang.Class的实例，
 * 而类里面的成员方法也都是对象,是java.lang.reflect.Method的对象
 * 类里面的成员变量也是对象，是java.lang.reflect.Field的对象
 * 类的构造函数也是对象，是java.lang.Construtor的对象
 * java.lang.Class的构造函数是私有的，只有JVM才能通过它的构造函数创建它的对象，
     * 不同于普通类的实例创建的Object obj = new Object();Object类的对象表达方式是new出来的obj变量，
 * 而反射机制下java.lang.Class的对象表达方式有三种:
 * 例如：编写了个类是public class Person{}
 *  这个person类就是java.lang.Class的实例，如何表达出来这个实例呢？
 * 表达方式一: Class c1 = Person.class;//从这里可以说明任何一个类都有一个隐含的静态成员变量class
 * 表达方式二: Person person1 = new Person();
 *           Class c2 = person1.getClass();
 * 表达方式三:Class.forName("com.core.reflect.Person");
 * */
public class ReflectClass {

    public static void main(String[] args) {

        Person person1 = new Person("Tom",10);
        //反射机制下获取java.lang.Class的实例Person类的类类型,有3种方式:
        //方式1：类名.class
        Class<?> c1 = Person.class;
        System.out.println("c1="+c1);

        //方式2：实例.getClass()
        Class<?> c2 = person1.getClass();
        System.out.println("c2="+c2);

        //方式3：Class.forName(包名下的类名)
        Class<?> c3 = null;
        try {
            c3 = Class.forName("com.core.reflect.Person");
            System.out.println("c3="+c3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //每个类都是java.lang.Class的一个实例
        System.out.println("c1==c2是"+(c1==c2));
        System.out.println("c1==c3是"+(c1==c3));

        //通过类的类类型创建类的实例
        try {
            Person person2 = (Person)c1.newInstance();//前提要求是：需要有无参数的构造方法
            System.out.println("person1.name="+person1.getName());
            System.out.println("person2.name="+person2.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //获取对象的方法信息
        System.out.println("---------------获取对象的方法信息---------------");
        ReflectClassUtil.printClassMessage(person1);

//        ReflectClassUtil.printClassMessage("hello world");

    }
}
