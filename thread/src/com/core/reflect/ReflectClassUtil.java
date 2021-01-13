package com.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 工具类-利用反射机制的API操作任意类的信息
 * */
public class ReflectClassUtil {

    /**
     * 打印类的信息，包括类的成员函数、成员变量
     * */
    public static void printClassMessage(Object obj){
        //要获取类的信息，首先要获取类的类类型
        Class<?> c = obj.getClass();
        //获取类的名称
        System.out.println("obj的类名是"+c.getName());

        /**
         * 成员方法信息是java.lang.reflect.Method类的对象
         * 通过类的类类型的 getMethods() 方法获取的是该类的public方法（包括继承自父类的）的Method对象数组(即该类的java.lang.reflect.Method对象数组)
         * getDeclaredMethods()获取的是该类自己的方法的Method对象数组,无论访问权限，但是不包含继承自父类的方法
         * */
        Method[] ms = c.getMethods();
//        Method[] ms2 = c.getDeclaredMethods();
        System.out.println("打印obj的成员方法:");
        for (Method m:ms
             ) {
            //得到方法的返回值类型的类类型
            Class<?> returnTypre = m.getReturnType();
//            System.out.print(returnTypre.getName()+" ");
            System.out.print(returnTypre.getSimpleName()+" ");//getSimpleName()是获取不带包名的名字
            //得到方法名
            System.out.print(m.getName()+" (");

            //获取参数类型--->得到的是参数列表的类型的类类型
            Class<?>[] paramTypes = m.getParameterTypes();
            Parameter[] params = m.getParameters();
            for (int i = 0; i < paramTypes.length; i++) {
                Class class1 = paramTypes[i];//参数类型
                String paramName = params[i].getName();//参数名
                System.out.print(" "+class1.getSimpleName()+" "+paramName);
                if(i<(paramTypes.length-1))
                {
                    System.out.print(",");
                }
            }
            System.out.println(" ){}");
        }


        System.out.println("打印obj的成员变量:");
        /**
         * 成员变量也是对象，是java.lang.reflect.Field的实例
         * */
        Field[] fs = c.getDeclaredFields();
        for( Field f : fs ){
            //得到成员变量的类型的类类型
            Class class2 = f.getType();
            String typeName = class2.getTypeName();//成员变量的类型的名字
            String fName = f.getName();//成员变量的名字
            System.out.println(typeName+" "+fName+";");
        }

        System.out.println("打印obj的构造函数:");
//        Constructor[] cs = c.getConstructors();
        Constructor[] cs = c.getDeclaredConstructors();
        for (Constructor cc:cs
             ) {
            System.out.print(cc.getName()+"(");
            Class[] ccParamTypes = cc.getParameterTypes();//构造函数的参数类型的类类型列表
            Parameter[] ccParams = cc.getParameters();//构造函数参数列表
            for (int i = 0; i < ccParamTypes.length; i++) {
                System.out.print(ccParamTypes[i].getSimpleName()+" "+ccParams[i].getName());
                if((i+1)!=ccParamTypes.length){
                    System.out.println(",");
                }
            }
            System.out.println("){};");
        }


    }



}
