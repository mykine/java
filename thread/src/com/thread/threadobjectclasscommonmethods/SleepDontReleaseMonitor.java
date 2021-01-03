package com.thread.threadobjectclasscommonmethods;

/**
 * 展示线程sleep的时候不释放synchronized的monitor，
 * 等sleep时间到了后，正常结束后才会释放
 * */
public class SleepDontReleaseMonitor implements Runnable {


    public static void main(String[] args) {
        SleepDontReleaseMonitor runnable = new SleepDontReleaseMonitor();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

    }

    @Override
    public void run() {
        try {
            syn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void syn() throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"获取到锁");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName()+"执行结束");
    }


}
