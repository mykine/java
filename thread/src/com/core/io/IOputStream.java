package com.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 1.字节流IO抽象类:
 * 输入流InputStream:抽象了读取文件数据的方法，以字节为单位操作，
 * 输出流OutputStream:抽象了将数据写入到文件的方法，以字节为单位操作，
 *
 * 2.EOF = END 读到-1就到结尾，表示结束
 *
 * 3.输入流InputStream基本方法
 *   int b = inputStream.read();//读取一个字节无符号填充到int最后8位,-1是EOF,可以用这个int变量是否等于-1判断是否到文件结尾
 *   byte[] btArr;inputStream.read(btArr);//一次性读取所有内容到字符数组
 *   byte[] btArr;inputStream.read(btArr,int start,int size);//读取字节数组中从start位置开始写size长度的字节到字节数组
 *
 * 4.输出流基本方法
 *   outPutStream.write(int b);//将int类型的b变量的末8位这个字节写入流中
 *   byte[] btArr="ABC".getBytes();outPutStream.write(btArr);//将字节数组所有内容到写入流中
 *   byte[] btArr;outPutStream.write(btArr,int start,int size);//将字节数组从start位置开始写size长度的字节到流中
 *
 * 5.继承字节流IO抽象类的子类:
 *  用于文件操作的子类:
 *  FileInputStream ——————> InputStream
 *
 *  用于网络传输的子类:
 *  FileOutPutStream ——————> OutputStream
 * */
public class IOputStream {

    public static void main(String[] args) throws Exception {
        String separator = File.separator;//获取当前操作系统的文件分隔符
        String filePath = separator+"tmp"+separator+"test_01"+separator+"test1.txt";
        String str = "中国";
        IOputStream.saveToFile(filePath,false,str);

//        File file = new File(filePath);
        IOputStream.printHex(filePath);
        System.out.println();
        System.out.println("------------------");
        IOputStream.printHexByByteArray(filePath);
    }

    /**
     *
     * 文件操作的字节流输入流FileInputStream读取文件
     * 读取指定的文件内容，按照16进制输出到控制台，并每输出10个byte就换行
     *
     */
    public static void printHex(String fileName) throws Exception {
        FileInputStream inputStream = new FileInputStream(fileName);
        int b;
        int i=1;
        //一个一个字节读取，读出来后转成int，存在int的后八位中,当读到-1时表示结束
        while ((b=inputStream.read())!=-1){
            System.out.print(Integer.toHexString(b)+" ");
            if( i % 10 == 0 ){
                System.out.println();
            }
            i++;
        }
        inputStream.close();

    }


    /**
     *
     * 文件操作的字节流输入流FileInputStream读取文件，
     *
     */
    public static void printHexByByteArray(String fileName) throws Exception {
        FileInputStream inputStream = new FileInputStream(fileName);
//        byte[] buff = new byte[10*1024];
        /**
         * 从inputStream中批量读取字节，放入到buff字节数组中,
         * 从0开始，读取数组长度的内容填充整个数组,
         * 返回的是读到的字节的个数,-1表示结束
         * */
//        int readCount = inputStream.read(buff,0,buff.length);
//        for (int i = 0; i < readCount; i++) {
//            System.out.print(Integer.toHexString(buff[i]&0xff)+" ");
//            if( i % 10 == 0 && i>0){
//                System.out.println();
//            }
//        }
        int readCount ;
        byte[] buff = new byte[3];
        //把字节数组当作缓存数组使用，重复装载读取文件内容
        while((readCount=inputStream.read(buff,0,buff.length))!=-1){
            for (int i = 0; i < readCount; i++) {
                System.out.print(Integer.toHexString(buff[i]&0xff)+" ");
                if( (i+1) % 3 == 0){
                    System.out.println();
                }
            }
        }

        inputStream.close();

    }

    /**
     * FileOutputStream继承了抽象类OutputStream,用于写入文件操作
     * */
    public static void saveToFile(String filepath ,boolean append,String str) throws  Exception {

        //如果文件不存在会直接创建,第二个参数表示是否追加写入，如果否，会删掉已存在的文件重新创建
        FileOutputStream outputStream = new FileOutputStream(filepath,append);

        outputStream.write('A');//写入A字符的低八位这个字节数据到文件
        outputStream.write('B');

        int a = 10;//write()只能写8位，写32位的int就需要写4次
        outputStream.write(a>>>24);//右移24位，左边自动补0，原最高8位，变成最后8位可以被write操作写入文件
        outputStream.write(a>>>16);
        outputStream.write(a>>>8);
        outputStream.write(a);

        byte[] bs = str.getBytes("gbk");
        outputStream.write(bs);

        outputStream.close();
    }


}
