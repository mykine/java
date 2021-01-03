package com.thread.interrupt;

/**
 * 错误的方式:通过volatile修饰的变量，让多个线程共享变量最新值，作为状态字段进行线程中断判断依据
 * 原理知识https://baijiahao.baidu.com/s?id=1595669808533077617&wfr=spider&for=pc，https://www.cnblogs.com/ustc-anmin/p/11434769.html
 * */
public class ErrorWayVolatileStopThread implements Runnable {

    private volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {

        ErrorWayVolatileStopThread runnable = new ErrorWayVolatileStopThread();

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        runnable.cancelExc();
        System.out.println(Thread.currentThread().getName()+"操作结束!");
    }

    @Override
    public void run() {
        int num =0;
        while( num <= 1000){
            num++;
//            System.out.println(Thread.currentThread().getName()+":"+num );
            try{
                if(canceled){
                    System.out.println(Thread.currentThread().getName()+":"+num+"，取消执行了");
                    break;
                }
                if( num % 100 ==0 ){
                    System.out.println(Thread.currentThread().getName()+":"+num+"是100的倍数");
                }
                Thread.sleep(10);//当线程在休眠过程中收到中断信号，会以抛出异常的方式响应中断
            }catch (Exception e){
                System.out.println(Thread.currentThread().getName()+"发生异常了!");
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"操作结束!");

    }

    public void  cancelExc(){
        canceled=true;
    }
}
