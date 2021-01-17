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
 *  通过keySet()方法获取HashMap的key集合(Set类型)，遍历key集合再通过get(key)获取元素值
 *
 *  HashMap内部实现原理：数组+链表,jdk1.8后加入红黑树进行了优化变成 数组 + 链表/红黑树
 *  hash算法:(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
 *          取得key对象的哈希code，并将它和它的无符号右移16位得到的值进行异或运算，最终计算出这对key-value数据节点在HashMap中的hash值
 *  存储的元素是内部类Node(实现了Entry接口)对象,每个Node包含
 *                  next(指向链表/红黑树下一个节点的指针)、
 *                  key(存储业务数据的key)、
 *                  value(存储业务数据的value)、
 *                  hash(通过key利用hash算法计算得到的hashcode)
 *                  等关键字段。
 *  为了解决hash冲突问题，HashMap采用数组+链表/红黑树的数据结构存储数据,(java1.8加入了红黑树进行优化)
 *  添加节点：会根据key的计算hash值，定位主干数组的位置，该位置上没有数据就将该节点放入，
 *  如果有数据说明hash冲突了，先判断该位置的旧数据是什么结构的，
 *      如果是链表结构，先判断该冲突的链表长度是否将大于阀值(默认8)，大于就将链表变成红黑树，按照红黑树存储数据，否则就遍历链表，存在相同的key就覆盖原有的值，否则新增节点加入链表,时间复杂度是O(n)
 *      如果是树结构，就根据红黑树的原理(左孩子都比自己小，右孩子都比自己大)，查找相同的key节点进行覆盖操作或新增节点加入红黑树
 *
 *  线程不安全:
 *  HashMap内对关键变量的修改操作没有加锁，多线程并发操作下，某个线程修改数据对于其他线程不可见，会出现数据被覆盖的情况,除此之外,
 *  java7版本在并发情况下，还可能会出现因扩容造成环形链表，导致cpu100%的问题:
 *  执行jdk1.7中扩容的关键函数是transfer(),默认将主干数组大小扩容2倍成新数组，然后调用transfer进行数据迁移，在该方法中，
 *  遍历旧数组，取出数组中每个链表的节点，刷新hash值并重新计算节点在新数组中的位置，通过头插法将节点放入新数组中的新链表中，
 *  比如新数组容量是newCapacity，当前操作的节点是e;
 *  Node next = e.next;
 *  int i = indexFor(e.hash,newCapacity);//hash冲突的链表的节点的hash相同，所以得到的新数组中位置的索引i也相同
 *  e.next = newTab[i];
 *  newTab[i] = e;
 *  e = next;
 *  多线程来回切换操作链表时，可能存在A->B,B->A的情况，也就造成环形链表，而且因为是通过遍历链表操作，会造成死循环，占用CPU100%
 *
 *  java8，改进了扩容,已经没有之前的transfer方法了，也通过引进红黑树来提高性能，但是在多线程并发情况下，仍然有数据覆盖的安全问题，
 *  比如rize方法中有个++size;方法内没有加锁，这样多个线程操作size时对外是不可见的，会有size值被覆盖或丢失的问题。
 *
 *  解决线程安全问题的办法：使用ConcurrentHashMap,内部采用了数据分段加锁的策略，
 *  多线程操作ConcurrentHashMap时，对于不同的数据段上的数据加的锁是不一样的，锁的范围较小，提高了性能，
 *  对于一段的数据，多个线程是在同一个锁下操作的，保证了数据的安全性
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
        System.out.println(map.get(999));
        map.remove(999);
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
