package com.core.io;

import java.io.*;

/**
 * 字符流:只用来操作文本文件的读写
 * 涉及基础概念:
 *      java的文本(char)是16位的无符号整数，是字符的unicode编码（双字节编码utf-16be）
 *      文件是byte byte byte...的数据序列（字节序列）
 *      文本文件是文本(char)序列按照指定编码方案(utf-8、utf-16be、gbk)序列化为byte序列的存储结果
 *      所以说字符流和字节流之间的转换涉及到编码问题
 * 字符流抽象类：Reader和Writer
 *    一次处理一个字符，字符的底层仍然是基本的字节序列
 * 字符流的实现类：
 *      InputStreamReader : 按照指定编码，将字节(byte)流解析为字符(char)流
 *      OutputStreamWriter : 按照指定编码，将字符(char)流转化为字节(byte)流
 * */
public class IOoutStreamRW {

    public static void main(String[] args) throws Exception {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test4.txt";
        String testStr="测试abc\nbeijing\r天安门\r\n";//注意:中间的\r使beijing在print打印时隐藏了

        IOoutStreamRW.w(filePath,"utf-8",testStr);
        System.out.println("-------------写1完毕,读1-----------");
        IOoutStreamRW.r(filePath,"utf-8");

        IOoutStreamRW.w2(filePath,testStr);
        System.out.println("-------------写1完毕,读1-----------");
        IOoutStreamRW.r2(filePath);


    }

    //读
    public static void r(String filepath,String encode) throws Exception {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath),encode);
        int result ;
        //read()每次读取一个字符，并将其转化为int,当是-1时表示结束
        while ((result=inputStreamReader.read())!=-1){
            System.out.print((char)result);
        }
        inputStreamReader.close();

    }

    //写
    public static void w(String filepath,String encode,String str) throws Exception {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filepath,false),encode);
        char[] chars = str.toCharArray();
        outputStreamWriter.write(chars);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    //读--用FileReader类
    public static void r2(String filepath) throws Exception {
        FileReader fr = new FileReader(filepath);
        System.out.println("encode="+fr.getEncoding());
        int result ;
        //read()每次读取一个字符，并将其转化为int,当是-1时表示结束
        while ((result=fr.read())!=-1){
            System.out.print((char)result);
        }
        fr.close();

    }

    //写--用FileWriter类,没法自定义编码，使用的是当前项目默认的编码
    public static void w2(String filepath,String str) throws Exception {
        FileWriter fw = new FileWriter(filepath,false);
        char[] chars = str.toCharArray();
        fw.write(chars);
        fw.flush();
        fw.close();
    }
}
