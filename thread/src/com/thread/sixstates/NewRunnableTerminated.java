package com.thread.sixstates;

/**
 * 展示线程的 NEW 、RUNNABLE、TERMINATED 状态,
 * */
public class NewRunnableTerminated implements  Runnable {

    public static void main(String[] args) throws InterruptedException {
        NewRunnableTerminated runnable = new NewRunnableTerminated();
        Thread thread = new Thread(runnable);
        //new()后，线程状态是:NEW
        System.out.println("new()后，线程状态是:"+thread.getState());

        thread.start();
        //start()后,线程状态是:RUNNABLE
        System.out.println("start()后,线程状态是:"+thread.getState());

        Thread.sleep(10);
        //执行run代码时,线程状态是:RUNNABLE
        System.out.println("执行run代码时,线程状态是:"+thread.getState());

        Thread.sleep(1000);
        //执行完毕,线程状态是:TERMINATED
        System.out.println("执行完毕,线程状态是:"+thread.getState());

    }

    @Override
    public void run() {
        int num=0;
        while(num<1000){
            num++;
            System.out.println(num );
        }
    }
}
