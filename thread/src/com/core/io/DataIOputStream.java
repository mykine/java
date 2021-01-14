package com.core.io;

import java.io.*;

/**
 * DataInputStream和DataOutputStream是对FileInputStream和FileOutputStream功能的扩展，
 * 可以将FileInputStream和FileOutputStream的实例作为参数传入DataInputStream和DataOutputStream的构造方法，
 * 也就是说DataInputStream和DataOutputStream实际是使用InputStream和OutputStream的实例（例如FileInputStream和FileOutputStream的对象）来操作数据读写，
 * DataInputStream和DataOutputStream，采用了"装饰者"模式，将基本的流读写操作装饰成可以利用各种数据类型进行读写的操作，可以方便读取int、long、字符等数据
 * 本质仍然是执行底层的的InputStream、OutputStream的字节类型的操作
 *
 * 常用方法:
 *  writeInt()/writeDouble()/writeUTF()
 * */
public class DataIOputStream {

    public static void main(String[] args) throws Exception {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test2.txt";
        DataIOputStream.writeData(filePath);
        System.out.println("写入完毕,执行读取：");
        DataIOputStream.readData(filePath);

    }



    public static void writeData(String filePath) throws Exception {

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath));

        dataOutputStream.writeInt(10);
        dataOutputStream.writeInt(-20);
        dataOutputStream.writeLong(30l);
        dataOutputStream.writeChars("ABC");//采用JAVA默认的字符编码utf-16be写入流中，一个中文占2个字节，一个英文占2个字节
        dataOutputStream.writeUTF("中国china");//采用uft-8写入流中，一个中文占3个字节，一个英文占1个字节

        dataOutputStream.close();
    }

    public static void readData(String filePath) throws Exception {

        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath));

        int a = dataInputStream.readInt();
        System.out.println(a);

        a = dataInputStream.readInt();
        System.out.println(a);

        long l = dataInputStream.readLong();
        System.out.println(l);

        byte[] abc = new byte[128];
        int readLenth= dataInputStream.read(abc,17,6);
        System.out.println(new String(abc));

        String china = dataInputStream.readUTF();
        System.out.println(china);

        dataInputStream.close();
    }


}
