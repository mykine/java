package com.thread.javasynchronized;

/**
 * 2个线程交替打印一个整数自增到10
 * */
public class IncrNumPrint {

    volatile static int num = 0;
    final  static Object obj1 = new Object();

    public static void main(String[] args){
        Thread t1 = new Thread( new IncrHandler(),"线程1");
        Thread t2 = new Thread( new IncrHandler(),"线程2");
        t1.start();
        t2.start();
    }


    static class IncrHandler implements Runnable{
        @Override
        public void run(){
            while (num < 10){
                synchronized (obj1){
                    try{
                        obj1.notify();
                        num++;
                        System.out.println(Thread.currentThread().getName()+":num="+num);
                        obj1.wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
