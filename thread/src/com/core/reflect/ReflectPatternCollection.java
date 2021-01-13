package com.core.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 方法的反射操作是编译后执行的，可以验证出集合的范型是去范型化的，
 * 在通过编译后，无论是定义了哪种类型的集合，利用反射操作可以往集合里加入任意类型的数据
 * 所以，集合的范型只是规范编码的输入类型范围，预防编码可能带来的错误，在编译时有效，但在反射机制面前无效
 * */
public class ReflectPatternCollection {

    public static void main(String[] args) {

        ArrayList list1 = new ArrayList();
        ArrayList<String> list2 = new ArrayList();

        //list1的类类型和list2的类类型是一致的,说明集合的范型是去范型化的，范型规约只在编译阶段有效
        System.out.println("list1.getClass()==list2.getClass()是"+(list1.getClass()==list2.getClass()));

        list2.add("jerry");
//        list2.add(1);//list2是定义了String类型的集合，添加非字符串的元素，在编译阶段不通过

        //但是可以通过反射实现让list2添加非字符串的元素
        Class c = ArrayList.class;
        Method m = null;
        try {
             m = c.getMethod("add",Object.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            Object result = m.invoke(list2,1);//编译阶段没有语法错误,编译通过后动态执行add方法
            System.out.println("通过反射实现将非String的元素添加进入范型集合list2了，reuslt="+result+"list2="+list2);
            //for循环就要注意元素类型问题了
            for (Object ob :list2
                 ) {
                System.out.println(ob);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
