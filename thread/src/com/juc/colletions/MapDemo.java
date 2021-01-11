package com.juc.colletions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 并发时要用相对高效并且线程安全的ConCurrentHashMap而不是普通的HashMap
 * Map的基本用法:并发时用Collections.synchronizedMap(New HashMap)同步加锁的方式保证并发安全
 * HashMap只读的并发时安全的，但是涉及到并发修改时，是不安全的
 * HashMap线程不安全，多个线程同时put时如果计算的hashcode一致,会造成数据丢失,
 * 而且在jdk1.7版本等旧版本时，高并发下HashMap动态扩容时会造成环形链表的情况，导致CPU占用100%的情况
 * jdk1.7的HashMap内部数据结构是拉链形式（数组+链表）的，装载若干个键值对数据桶的数组，通过链表形式把相同hashcode的数据存起来
 * 当计算的hash值重复时，就在对应数组桶的位置添加链表节点,用next指向这个相同hashcode的数据桶
 * jdk1.8开始在HashMap的内部谁结构中添加了红黑树，当拉链形式的节点过多(默认大于8)，会升级成红黑树存储,将查询复杂度将O(N)降为O(logN)
 * */
public class MapDemo {

    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>(2,1.5f);
        map.put(1,"tom");
        map.put(3,"jerry");
        map.put(5,"lucy");
        map.put(7,"lily");//4>2*1.5 会扩容
        System.out.println(map.size());
        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.get(3));
        System.out.println(map.containsKey(1));

        concurrentHashMap();

    }

    /**
     * 并发时要用相对高效并且线程安全的 ConCurrentHashMap 而不是普通的HashMap
     * java7中的ConCurrentHashMap内部数据结构是：包含默认的16个数据段Segment(继承了ReentrantLock),
     *      每个Segment存储拉链式HashMap数组,每个Segment独立上ReentrantLock锁，互不影响，允许并发访问，提高了并发效率
     * *****************************************************************
     * Java8中ConCurrentHashMap内部数据结构是：包含若干个独立的Node节点，每个节点是拉链+红黑树两种形态存储
     *      采用CAS+synchronized并存的加锁方式解决并发安全问题
     * */
    static  void concurrentHashMap(){
        System.out.println("*********************并发下要用ConcurrentHashMap*********************");
        ConcurrentHashMap<String,String> cHashMap = new ConcurrentHashMap<>();
        System.out.println(cHashMap.put("tom","汤姆"));
        System.out.println(cHashMap.put("jerry", "杰瑞"));
        System.out.println(cHashMap.put("jerry", "杰瑞2"));
        System.out.println(cHashMap.get("jerry"));
        System.out.println(cHashMap);

    }

}
