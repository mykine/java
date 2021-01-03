package com.thread.sixstates;

/**
 * 展示线程的 BLOCKED,WAITING,TIMED_WAITING 状态,
 * */
public class BlockedWaitingTimedWaiting implements  Runnable {

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        //thread1首先获取syn()方法锁后执行休眠时，线程状态是:TIMED_WAITING
        System.out.println("thread1首先获取syn()方法锁后执行休眠时，线程状态是:"+thread1.getState());

        //thread2等待thread1释放syn()方法锁时，线程状态是:BLOCKED
        System.out.println("thread2等待thread1释放syn()方法锁时，线程状态是:"+thread2.getState());

        Thread.sleep(1500);
        //thread1休眠结束被唤醒后执行wait()方法时，线程状态是:WAITING
        System.out.println("thread1休眠结束被唤醒后执行wait()方法时，线程状态是:"+thread1.getState());

        //thread2仍然在等待thread1释放syn()方法锁，线程状态是:TIMED_WAITING
        System.out.println("thread2仍然在等待thread1释放syn()方法锁，线程状态是:"+thread2.getState());
    }

    @Override
    public void run() {
        syn();
    }

    /**
     * (多个线程使用的同一个rannable对象时)首次执行synchronized修饰的方法的线程时会持有锁,
     * 后面的线程在等待锁的情况下会进入BLOCKED状态
     * */
    public synchronized void syn(){
        try {
            Thread.sleep(1000);//拿到锁的线程休眠期间会进入TIMED_WAITING状态
            wait();//休眠完唤醒后执行wait方法，然后会进入WAITING状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
