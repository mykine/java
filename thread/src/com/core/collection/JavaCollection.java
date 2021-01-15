package com.core.collection;

import java.util.*;

/**
 * Collection是顶层接口，List接口和Set接口继承Collection,
 *
 * List的元素按照所以顺序存储，可以存储相同值的元素，
 *      ArrayList类和LinkedList类实现了List接口
 *      ArrayList:可变长度的数组，连续的内存空间
 *                优点:可以随机访问，查询效率高
 *                缺点:新增、删除元素需要维护数组长度，效率低
 *                特点:ArrayList像装集装箱轮船
 *                适用场景:随机查询比较多，增删操作相对比较少时
 *
 *      LinkedList:链表实现，内存空间不连续
 *                 优点:新增、删除只需要动态维护单个节点，效率比数组高
 *                 缺点:查询需要遍历链表，效率低
 *                 特点:LinkedList像一节一节车厢的火车
 *                 适用场景:增删操作比较频繁，随机查询相对比较少时
 *
 * Set的元素存储无顺序，所以无法直接通过索引读取元素，存储的元素值是唯一的
 *      HashSet实现了Set接口:
 *          通过Iterator或foreach遍历元素
 *
 *
 * */
public class JavaCollection {

    public static void main(String[] args) {
//        arrayListDemo();
//        linkedListDemo();

          setDemo();
    }

   /**
    * Set Demo
    * Set特点是不会存在重复元素,存储无序，无法通过索引获取元素,要通过iterator或foreach遍历元素
    * 遍历Set所有元素时会发现，读取出来的元素顺序和当初添加他们时的顺序不一致，就说明了Set是无序存储的
    * */
    public static void setDemo(){
        Set set = new HashSet();
        Demo1 tom = new Demo1(1001, "tom");
        set.add(tom);
        set.add(tom);
        set.add(tom);//实际上只会保存一个tom
        set.add("12");//存储各种类型
        set.add(999);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            if(obj instanceof  String){
                System.out.println(obj+"元素是字符串");
            }else if(obj instanceof Integer){
                System.out.println(obj+"元素是整数");
            }else if(obj instanceof Demo1){
                System.out.println(obj+"元素是整数");
            }else{
                System.out.println(obj+"类型未知");
            }
        }

        //如果能确保Set中所有元素的类型都一致，就可以用foreach遍历元素了
        System.out.println("如果能确保Set中所有元素的类型都一致，就可以用foreach遍历元素了");
        Set<Demo1> set2 = new HashSet<Demo1>();
        set2.add(new Demo1(1,"jerry1"));
        set2.add(new Demo1(2,"jerry2"));
        set2.add(new Demo1(3,"jerry3"));
        for (Demo1 demo:set2
             ) {
            System.out.println(demo);
        }

    }

    /**
     * ArrayList Demo
     * */
    public static void arrayListDemo() {

        /**
         * 不传初始化数组容量时会创建一个空数组，在第一次调用add方法时创建一个默认容量(10)的数组，
         * 以后每次add都会检测是否需要扩容改变数组容量，再执行添加元素操作
         * 扩容源码： int newCapacity = oldCapacity + (oldCapacity >> 1);
         * 等价于:int newCapacity = oldCapacity + (oldCapacity / 2)
         * 即每次自动扩容1.5倍
         *
         * 注意：数组容量和数组大小的区别，
         *      数组容量是申请的内存大小，是预留装载元素的空间大小
         *      数组大小是只已经装载的元素个数
         * */
        List list1 = new ArrayList();
        System.out.println("元素个数="+list1.size());
        list1.add(new Demo1(1,"demo1"));
        System.out.println("元素个数size="+list1.size());
        Demo1 demo2 = new Demo1(2, "demo2");
        list1.add(demo2);
        list1.add(new Demo1(3,"demo3"));
        list1.add(new Demo1(4,"demo4"));
        list1.add(new Demo1(5,"demo5"));
        list1.add(new Demo1(6,"demo6"));
        list1.add(new Demo1(7,"demo7"));
        list1.add(new Demo1(8,"demo8"));
        list1.add(new Demo1(9,"demo9"));
        list1.add(new Demo1(10,"demo10"));
        list1.add(new Demo1(11,"demo11"));
        list1.add("test");
        System.out.println("元素个数size="+list1.size());
        for (int i = 0; i < list1.size(); i++) {
            Demo1 demo1 = (Demo1) list1.get(i);
            System.out.println(demo1.getId() + ":" + demo1.getName());
        }
        System.out.println("--------------------------移除2后------------------------------");
        list1.remove(demo2);
        System.out.println("元素个数size="+list1.size());
        for (int i = 0; i < list1.size(); i++) {
            Demo1 demo1 = (Demo1) list1.get(i);
            System.out.println(demo1.getId() + ":" + demo1.getName());
        }

    }

    /**
     * LinkedList Demo
     * */
    public static void linkedListDemo() {
        /**
         * 链表结构,可以在任意指定的位置删减节点,高效添加删除元素
         * 但是查询会遍历整个链表，查询曹走效率低下
         * */
        List list1 = new LinkedList();
        System.out.println("元素个数="+list1.size());
        list1.add(new Demo1(1,"demo1"));
        System.out.println("元素个数size="+list1.size());
        Demo1 demo2 = new Demo1(2, "demo2");
        ((LinkedList) list1).addFirst(demo2);
        Demo1 demo3 = new Demo1(3, "demo3");
        list1.add(demo3);
        System.out.println("元素个数size="+list1.size());
        for (int i = 0; i < list1.size(); i++) {
            Demo1 demo1 = (Demo1) list1.get(i);
            System.out.println(demo1.getId() + ":" + demo1.getName());
        }
        System.out.println("--------------------------移除首个元素后------------------------------");
        ((LinkedList) list1).removeFirst();
        System.out.println("元素个数size="+list1.size());
        for (int i = 0; i < list1.size(); i++) {
            Demo1 demo1 = (Demo1) list1.get(i);
            System.out.println(demo1.getId() + ":" + demo1.getName());
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


