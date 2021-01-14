package com.core.io;

import java.io.*;

/**
 * BufferedInputStream&BufferedOutputStream这两个IO流带缓冲区，会将数据先加载到缓冲区，
 * 当缓冲区满了时，或想主动刷新缓冲区时，再将缓冲区的数据写入磁盘,充分利用了内存的效率，从而提高了IO性能
 * 缓冲区大小默认是8Kb=8*1024=8192byte
 * */
public class BufferedIOputStream {

    public static void main(String[] args) throws Exception {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePathFrom = separator+"tmp"+separator+"test_01"+separator+"test2.txt";
        String filePathTo = separator+"tmp"+separator+"test_01"+separator+"test3.txt";
        System.out.println("copy前,fromFile内容:");
        IOputStream.printHexByByteArray(filePathFrom);

        copy(filePathFrom,filePathTo);

        System.out.println("copy后,toFile内容:");
        IOputStream.printHexByByteArray(filePathFrom);

    }

    public static void copy(String fromPath,String toPath) throws Exception {
        File fromFile = new File(fromPath);
        if(!fromFile.exists()){
            throw new IllegalArgumentException("文件:"+fromPath+"不存在");
        }
        File toFile = new File(toPath);
        if(!toFile.exists()){
            throw new IllegalArgumentException("文件:"+toFile+"不存在");
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile));

        int a;
        while ((a=bis.read())!=-1){
            bos.write(a);
            bos.flush();//手动刷新缓冲区
        }

        bis.close();
        bos.close();

    }
}
