package com.juc.cas;

/**
 * CAS是乐观锁的实现算法，CAS对应了CPU的一些操作指令，由CPU层面来保证原子性操作，
 * CAS算法是：提交给CPU操作时，每个线程对于共享资源有3个值(1.内存中真实的现值，2.预期值，3.要修改成为的值)，
 *          CPU会判断预期值和内存中真实的现值是否相等，
 *          如果是，就修改值，
 *          否则就知道了有其他线程修改了共享资源，不修改当前线程要改的值，当前线程不操作或抛异常等
 * 缺点：CAS只判断值是否相等，不判断期间是否执行过，可能会出现ABA问题，原始值是A，先修改成B，再修改成B，这时CAS可能就判断不出来有其他线程操作过了
 * 应用场景：乐观锁、并发容器、原子类
 * 演示：模拟CAS原理,等价代码
 * */
public class SimulatedCAS implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        SimulatedCAS r = new SimulatedCAS();
        r.val = 0;
        Thread thread1 = new Thread(r);
        Thread thread2 = new Thread(r);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("val="+r.val);
    }

    public static int val = 0;

    @Override
    public void run() {
        comparedAndSet(0,1);
    }

    private int comparedAndSet(int expectedVal,int newVal){
        synchronized(Runnable.class){
            System.out.println(Thread.currentThread().getName()+"预期值="+expectedVal+",当前内存中值="+this.val+",要改成的值="+newVal);
            if(expectedVal==this.val){
                System.out.println(Thread.currentThread().getName()+",执行修改的值="+newVal);
                this.val = newVal;
            }
        }
        return this.val;
    }
}
