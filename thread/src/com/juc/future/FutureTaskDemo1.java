package com.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask实现了RunnableFuture接口，而RunnableFuture接口继承了Runnable、Future两个接口,
 * 所以FutureTask既可以被线程Thread调用，又可以获取线程的返回值
 * */
public class FutureTaskDemo1 {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
        //FutureTask作为子线程执行的任务对象
        new Thread(integerFutureTask).start();
        Thread.sleep(2000);
        try {
            //FutureTask直接获取子线程执行的结果
            System.out.println("结果="+integerFutureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println(Thread.currentThread().getName()+"执行中..");
            Thread.sleep(1000);
            int num=0;
            for (int i = 0; i < 1000; i++) {
               num++;
            }
            return num;
        }
    }

}
