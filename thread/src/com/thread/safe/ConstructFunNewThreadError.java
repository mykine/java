package com.thread.safe;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 在构造函数新建线程，容易造成空指针等错误
 *
 * */
public class ConstructFunNewThreadError {

   private HashMap<String,String> hashMap = null;

    public ConstructFunNewThreadError() {
        //在构造函数中新建线程，会造成线程安全问题
        new Thread(new Runnable() {
            @Override
            public void run() {
                hashMap = new HashMap<>();
                hashMap.put("1","111");
                hashMap.put("2","222");
                hashMap.put("3","333");
            }
        }).start();

    }

//    public HashMap<String,String> getHashMap(){
//        return this.hashMap;//直接将私有变量的引用开放出去，可以被外部类调用时进行修改操作，会造成线程安全问题
//    }

    public HashMap<String,String> getHashMap(){
        return new HashMap<>(this.hashMap);//将私有变量的副本开放出去，即可解决线程安全问题
    }


    public static void main(String[] args) {
        ConstructFunNewThreadError obj = new ConstructFunNewThreadError();
        System.out.println("1="+obj.hashMap.get("1"));

    }

}
