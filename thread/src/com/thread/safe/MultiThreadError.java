package com.thread.safe;

public class MultiThreadError implements  Runnable {

   static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        MultiThreadError r = new MultiThreadError();
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("num="+num);

    }


    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

}
