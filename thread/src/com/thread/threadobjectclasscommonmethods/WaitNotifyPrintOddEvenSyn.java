package com.thread.threadobjectclasscommonmethods;

/**
 * 利用两个线程交替打印100内的奇偶数
 * 只使用synchronized方式
 * */
public class WaitNotifyPrintOddEvenSyn {
    static int num=0;
    static Object lock=new Object();
    public static void main(String[] args) {

        //偶数线程打印偶数(位运算&1结果是0的是偶数)
        Thread threadEvenNum = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<100){
                    synchronized (lock){
                        if( (num & 1) == 0){
                            System.out.println(Thread.currentThread().getName()+":"+(num++));
                        }else{
//                            System.out.println(Thread.currentThread().getName()+"无效操作:num="+num);
                        }
                    }
                }
            }
        },"偶数");
        //奇数线程打印奇数,(位运算&1结果是1的是奇数)
        Thread threadOddNum = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num<100){
                    synchronized (lock){
                        if( (num & 1) == 1){
                            System.out.println(Thread.currentThread().getName()+":"+(num++));
                        }else{
//                            System.out.println(Thread.currentThread().getName()+"无效操作:num="+num);
                        }
                    }
                }
            }
        },"奇数");

        threadEvenNum.start();
        threadOddNum.start();
    }
}
