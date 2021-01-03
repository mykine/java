package com.thread.interrupt;

/**
* run方法内每次循环都有sleep时,在循环内捕获异常时Thread.currentThread().isInterrupted()无效果,线程循环程序不会停止，
 * 因为Java的sleep函数收到中断信号并响应后会清除interrupt标记
* **/
public class CantInterruptInLoopTryCatch {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num =0;
            while(  num <= 10000){
                num++;
                if( num % 100 ==0 && !Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+":"+num+"是100的倍数");
                }
                try {
                    Thread.sleep(10);//当线程在休眠过程中收到中断信号，会以抛出异常的方式响应中断

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+"发生异常了哦!");
                }
            }

            System.out.println(Thread.currentThread().getName()+"操作结束!");

        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
        System.out.println(Thread.currentThread().getName()+"操作结束!");
    }

}
