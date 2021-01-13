package com.core.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 利用反射原理操作执行类的方法
 * */
public class ReflectOperate {

    public static void main(String[] args) {

        Person person1 = new Person("Tom",10);

        //首先获取Person类的类类型
        Class c = person1.getClass();

        //通过Person类的类类型获取想要的方法对象
        try {
            //第二个参数Class<?>... parameterType是三个点表示是可变参数，可以直接传数组，也可以一个个写出来传
//            Method m = c.getMethod("print",new Class[]{int.class,int.class});
            Method m = c.getMethod("print",int.class,int.class);//从类的类类型获取到想要执行的方法对象
            //通过Method对象来实现方法的反射调用,达到person1.print(1,2);一样的效果
            try {
                int result = person1.print(1,2);
                System.out.println("普通操作，通过Person的对象person1直接执行print(int,int)方法,result="+result);
                Object result1 = m.invoke(person1, new Object[]{1, 2});
                System.out.println("利用反射机制操作，通过Person的类类型的Method对象的invoke方法反过来操作person1执行print(int,int)方法,result="+result1);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
