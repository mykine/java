package com.core.io;

import java.io.*;

/**
 * 带缓冲区的字符流过滤器:缓冲区大小默认8Kb
 * BufferedReader:可以使用readLine方法实现一行一行的读,对Reader流进行过滤
 * BufferedWriter:可以写一行,也可以写完就换行,对Writer流进行过滤
 *
 * */
public class BufferedRW {

    public static void main(String[] args) throws Exception {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test5.txt";
        String filePath2 = separator+"tmp"+separator+"test_01"+separator+"test6.txt";
        String testStr="1天安门\r\n\n2天安门\r\n\n3天安门\r\n\n";

        BufferedRW.w(filePath,"utf-8",testStr);
        System.out.println("\r\n-------------写1完毕,读1-----------");
        BufferedRW.r(filePath,"utf-8");

        System.out.println("\n-------------写2完毕,读2-----------");
        BufferedRW.r2(filePath,"utf-8",filePath2);

        System.out.println("\n-------------读2-----------");
        BufferedRW.r(filePath2,"utf-8");

    }

    public static void r(String filepath,String encode) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),encode)) ;
        String str;
        //readLine一行一行的读，通过识别到\r\n或\r或\n时就认为是换行，取出有效内容值(不包含这些换行符或回车符)
        while ((str=reader.readLine())!=null){
            System.out.print(str);
        }
        reader.close();
    }

    public static void w(String filepath,String encode,String content) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(filepath, false);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encode);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter,10*1024);//缓冲区大小设置成10Kb，默认是8Kb
        writer.write(content);
//        writer.write(content+"\r\n");
//        writer.write(content+"\r\n");
//        writer.write(content+"\r\n");
        writer.flush();

        writer.close();
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public static void r2(String filepath,String encode,String toFilepath) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),encode)) ;
        String str;
        //readLine一行一行的读，通过识别到\r\n或\r或\n时就认为是换行，取出有效内容值(不包含这些换行符或回车符)
        int rowNum=0;
        while ((str=reader.readLine())!=null){
            rowNum++;
            System.out.print(";第"+rowNum+"行内容是："+str);
            w(toFilepath,encode,str);
        }
        reader.close();
    }
}
