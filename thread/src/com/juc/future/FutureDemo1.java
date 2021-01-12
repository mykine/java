package com.juc.future;

import java.util.concurrent.*;

/**
 * Future可以接收子线程执行Callable的call方法返回的结果
 * 主线程调用future.get()时，如果子线程还未执行完，主线程会进入阻塞状态，直到子线程执行完毕将结果填充到future才会被唤醒
 * */
public class FutureDemo1 {

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                5,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        Callable calable = new Callable() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+"执行计算");
                Thread.sleep(2000);
                return 999;
            }
        };
        Future future = pool.submit(calable);
        try {
            Integer result = (Integer) future.get();
            System.out.println(Thread.currentThread().getName()+"-result="+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();

    }
}
