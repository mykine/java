package com.thread.interrupt;

/**
 * run方法内没有sleep或wait等阻塞操作
 * */
public class RightWayStopThreadWithoutSleep implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num= 0 ;
        while ( !Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE /2 ){
            num++;
            if(num%10000 == 0){
                System.out.println(num+"是10000的倍数");
            }
        }
        System.out.println("操作结束");
    }
}
