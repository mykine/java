package com.core.io;

import java.io.File;
import java.io.IOException;

/**
 * File类只能用于表示文件(目录)的信息(名称、大小、路径等),不能用于文件内容的访问
 * */
public class JavaFile {

    public static void main(String[] args) {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator;
        String fileName = "test1.txt";

        System.out.println(filePath+fileName);
        File file = new File(filePath+fileName);
//        File fileFolder = new File(filePath);
        File fileFolder = file.getParentFile();
        System.out.println(fileFolder);//打印的是fileFolder的toString()
        System.out.println(file.getAbsolutePath());
        if(!fileFolder.exists()){
            boolean res = fileFolder.mkdirs();//递归创建文件夹
//            boolean res = fileFolder.mkdir();//创建文件夹
            System.out.println("递归创建文件夹,res="+res);
        }
        System.out.println(file.getName()+" exists is "+file.exists());

        //当不是目录或目录不存在时，返回false
        System.out.println("fileFolder是否是个目录:"+fileFolder.isDirectory());
        //是否是个文件,文件不存在时返回false
        System.out.println("file是否是个文件:"+file.isFile());

        //生成文件
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //是否是个文件,文件不存在时返回false
        System.out.println("file是否是个文件:"+file.isFile());
//        System.out.println("delete file!!!");
//        if(file.exists()){
//            file.delete();
//            System.out.println(file.getName()+" exists is "+file.exists());
//        }



    }
}
