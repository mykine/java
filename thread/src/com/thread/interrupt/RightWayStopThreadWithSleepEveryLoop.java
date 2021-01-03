package com.thread.interrupt;

/**
 * run方法内每次循环都有sleep时,在循环外捕获异常时无需判断Thread.currentThread().isInterrupted(),线程休眠阻塞时收到中断信号时会抛异常中断循环
 * */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num =0;
            try {
                while(  num <= 10000){
                    num++;
                    if( num % 100 ==0 ){
                        System.out.println(Thread.currentThread().getName()+":"+num+"是100的倍数");
                    }
                    Thread.sleep(10);//当线程在休眠过程中收到中断信号，会以抛出异常的方式响应中断

                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName()+"发生异常了!");
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
