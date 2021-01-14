package com.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * RandomAccessFile是java提供的对文件内容的访问，既可以读文件，也可以写文件
 * 支持随机访问文件，可以访问文件的任意位置
 *
 * 1.java文件模型：
 *   在硬盘上的文件是byte存储的，是字节的集合
 *
 * 2.打开文件,有两种模式"rw"（读写）和"r"（只读）
 * RandomAccessFile raf = new RandomAccessFile(new File("/tmp/test.txt"),"rw");
 * 文件指针,打开文件时指针在开头 pointer=0
 *
 *  3.写方法
 *    raf.write(int) ---> 一次只写一个字节(后8位)，每写完一个字节后指针指向下一个位置，准备再次写入
 *
 *  4.读方法
 *  int b = raf.read()--->每次读一个字节,读完后将字节转换为整数
 *
 *  5.文件读写完成后一定要关闭
 *
 * */
public class JavaRandomAccessFile {
    public static void main(String[] args) throws Exception {

        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test1.txt";
        File file = new File(filePath);

        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        System.out.println("当前指针位置:"+raf.getFilePointer());

        raf.write('a');//只写后8位
        System.out.println("当前指针位置:"+raf.getFilePointer());

        int i = 9;
//        raf.writeInt(i);//将整数(32位)分成4部分写入
        //等效代码：等分4次，依次将整数的32位写入
        raf.write(i>>>24);//第一次写入最高8位
            System.out.println("当前指针位置:"+raf.getFilePointer());
        raf.write(i>>>16);//第2次写入第2高8位
            System.out.println("当前指针位置:"+raf.getFilePointer());
        raf.write(i>>>8);//第3次写入第3高8位
            System.out.println("当前指针位置:"+raf.getFilePointer());
        raf.write(i);//第4次写入最后的8位，所以可知write()方法只会将最后8位写入
            System.out.println("当前指针位置:"+raf.getFilePointer());

        String str = "中";
        byte[] bytes  = str.getBytes("gbk");//gbk编码下,中文占2个字节
        raf.write(bytes);
        System.out.println("当前指针位置:"+raf.getFilePointer());

        System.out.println("读文件必须把指针移到头部");
        raf.seek(0);
        System.out.println("把文件中的内容一次性读到字节数组中:");
        byte[] content = new byte[(int)raf.length()];
        raf.read(content);
        System.out.println(Arrays.toString(content));
        for (byte b:content
             ) {
            //以十六进制值输出，做与运算，将高8位的0去掉,显示简洁
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }
        System.out.println();
        System.out.println(new String(content,"gbk"));



        raf.close();

    }


}
