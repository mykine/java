package com.core.io;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class Encode {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String s = "哈喽ABC";
        //获取字节数组
        byte[] byte1 = s.getBytes();//不传编码格式，转换字节序列将使用当前项目默认的编码
        System.out.println("使用当前项目默认的编码(utf-8)");
        for (byte b:byte1
             ) {
            //将字节以16进制数
            System.out.print(Integer.toHexString(b & 0xff )+" ");
        }

        System.out.println();
        System.out.println();
        System.out.println("utf-8编码下英文占一字节、中文占3个字节");
        byte[] bytes1 = s.getBytes("utf-8");
        for (byte b:bytes1
             ) {
            System.out.print(Integer.toHexString(b & 0xff )+" ");
        }

        System.out.println();
        System.out.println();
        System.out.println("gbk编码下英文占一字节、中文占2个字节");
        byte[] bytes2 = s.getBytes("gbk");
        for (byte b:bytes2
                ) {
            System.out.print(Integer.toHexString(b & 0xff )+" ");
        }
        System.out.println();
        System.out.println();
        System.out.println("JAVA是双字节编码utf-16be,英文占2个字节、中文也占2个字节");
        byte[] bytes3 = s.getBytes("utf-16be");
        for (byte b:bytes3
             ) {
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }

        System.out.println();
        System.out.println();
        System.out.println("GB2312编码下英文占一字节、中文占2个字节");
        byte[] bytes4 = s.getBytes("GB2312");
        for (byte b:bytes4
                ) {
            System.out.print(Integer.toHexString(b & 0xff)+" ");
        }

        System.out.println();
        System.out.println();
        //字节序列是某种编码时，转成字符串时也要用相同的编码方式,否则会出现乱码
        String str = new String(bytes3,"utf-16be");
        System.out.println("str="+str);

        /**
         * 文本文件的内容就是字节序列,可以是任意编码的字节序列,
         * 如果在中文机器上创建文本文件保存内容，那么该文本文件只认识ansi编码(即gbk编码)
         * */
        String filePth = "/tmp/test1.txt";
        File file = new File(filePth);

    }
}
