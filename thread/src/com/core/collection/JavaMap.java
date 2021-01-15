package com.core.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map接口是键值对数据结构,与Collection是同级关系
 * 不带泛型的情况，Map的key、value均可以同时存储任意类型的值
 * 实现Map接口的类有:HashMap
 *  HashMap
 *  通过keySet()方法获取HashMp的key集合(Set类型)，遍历key集合再通过get(key)获取元素值
 * */
public class JavaMap {

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1001","1001");
        map.put("1001",new Demo1(1001,"tom1"));
        map.put("1002",new Demo1(1002,"tom2"));
        map.put(999,new Demo1(999,"tom999"));
        Demo1 d1 = new Demo1(1,"demo1");
        Demo1 d2 = new Demo1(2,"demo2");
        map.put(d1,"d1");
        map.put(d2,22222);

        Set keysSet = map.keySet();
        Iterator iterator = keysSet.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();//每次调用next()方法，会返回一个元素，并且指针会下移一位
            System.out.println("-------------------");
            System.out.print("key="+obj);
            System.out.println(",value="+map.get(obj));
        }

    }

    static class Demo1{
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Demo1(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.hashCode()+":id="+id+",name="+name;
        }
    }
}
