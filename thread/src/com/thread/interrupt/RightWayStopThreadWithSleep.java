package com.thread.interrupt;

/**
 * run方法内有sleep时,线程阻塞时中断
 * */
public class RightWayStopThreadWithSleep  {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num =0;
            while( !Thread.currentThread().isInterrupted() && num <= 300){
                num++;
                if( num % 100 ==0 ){
                    System.out.println(Thread.currentThread().getName()+":"+num+"是100的倍数");
                }
            }
            System.out.println(Thread.currentThread().getName()+"操作结束!");
            try{
                Thread.sleep(1000);//当线程在休眠过程中收到中断信号，会以抛出异常的方式响应中断
            }catch (Exception e){
                System.out.println(Thread.currentThread().getName()+"发生异常了!");
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        System.out.println(Thread.currentThread().getName()+"操作结束!");
    }


}
