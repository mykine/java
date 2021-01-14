package com.core.io;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 * */
public class FileUtils {

    public static void main(String[] args) {
        String dir = "/tmp/test1";
    }

    public static void listDirectory(File dir) throws IOException{
        if(!dir.exists()){
            throw new IllegalArgumentException("目录"+dir+"不存在!");
        }
        if(!dir.isDirectory()){
            throw new IllegalArgumentException(dir+"不是目录!");
        }

        File[] files = dir.listFiles();
        if(files!=null && files.length>0){
            for (File f:files
                 ) {
                if(f.isDirectory()){
                    listDirectory(f);
                }else{
                    System.out.println(f);
                }
            }
        }

    }
}
