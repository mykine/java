package com.core.io;

import java.io.*;

/**
 * 对象的序列化，反序列化
 * 对象的序列化就是将Object转换为byte序列,反之叫对象的反序列化
 * 序列化流:ObjectOutStream,是过滤流----writeObject
 * 反序列化流:ObjectInputStream---readObject
 *
 * 序列化接口Serializable
 *
 * 对象所属的类(或父类)必须实现序列化接口，才能进行序列化，否则将出现异常
 * 这个接口，没有任何方法，只是一个标准
 *
 * 子类的父类如果没有实现序列化接口，子类创建对象时，不会调用父类的构造函数
 *
 * transient关键字修饰的字段不会进行JVM默认的序列化
 *
 * */
public class SerializableDemo implements Serializable {

    private long id;
    private String name;
    private transient int age;//transient关键字修饰的字段不会进行JVM默认的序列化

    public SerializableDemo(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "id="+id+",name="+name+",age="+age;
    }

    public static void main(String[] args) throws Exception {
        SerializableDemo obj = new SerializableDemo(10001L,"tom",10);

        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test7.txt";
        System.out.println("执行序列化。。");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(obj);
        oos.close();

        System.out.println("执行反序列化。。");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        SerializableDemo obj2 =  (SerializableDemo)ois.readObject();
        ois.close();
        System.out.println(obj2);

    }

    //对象的序列化
    public static void serialz(String filepath,Object obj){

    }

    //对象的反序列化
    public static void unserialz(String filepath,Object obj){

    }

}

