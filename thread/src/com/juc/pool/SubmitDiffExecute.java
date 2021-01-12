package com.juc.pool;

import java.util.concurrent.*;

/**
 * ExecutorService的submit()方法和execute()方法的不同
 * 1.submit()可以接收Runnable和Callable两种参数,在submit内部都会被包装成FutureTask对象,
 *   FutureTask实现了RunnableFuture接口，而RunnableFuture接口继承了Runnable接口和Future接口，submit()有返回值，返回的是Future对象
 *   execute()只接收Runnable参数，无返回值
 *
 * 2.submit()有返回值，返回Future<T>对象，通过Future的get()获取子线程的结果，主线程调用future.get()时如果子线程还未执行完，主线程会进入阻塞状态，直到子线程执行完将结果填充到future才会被唤醒
 *   execute()没有返回值
 *
 * 3.处理异常的不同：
 * 对于CheckedException:
 *      当参数是Runnable时，无论submit还是execute,在子线程内,都只能在run()内部通过try...catch捕获异常，无法向上throw抛出,因为Java中Runnable的run()方法不允许主动向外抛出异常,只在run()内部捕获异常;
 *      submit()可接收Callable参数，Callable的call方法可以向外抛出CheckedException,当然也可以抛出UnCheckedException
 * 对于UnCheckedException:
 *      execute方法在子线程内会抛出运行时异常,调用线程可以通过实现UncaughtExceptionHandle接口处理UncheckedException;
 *      submit方法在子线程内，最终执行的是FutureTask的run方法，该方法内会通过try...catch捕捉异常并不再抛出，但是可以通过调用Future的get()时将捕捉的异常重新抛出,
 *          要注意的是无论参数是Runnable还是Callable,如果不对返回值Future调用get()方法，异常都会被"吃掉",
 *          Future的get方法会捕获Callable的call方法执行时抛出的异常，并包装成统一的ExecutionException再向调用线程(主线程)抛出
 *
 * */
public class SubmitDiffExecute {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor pools = new ThreadPoolExecutor(
                5,
                5,
                1,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        System.out.println("------------可检查到的异常（CheckedException）案例：-------------");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    throw new Exception(" runnable的run里的异常只能被内部捕获，抛不出去");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName()+e.getMessage());
                }
            }
        };

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new Exception(Thread.currentThread().getName()+" callable的call里的异常直接抛出去了");
            }
        };

        //CheckedExption---submit方法用法一:接收Runnable参数
        Future<?> f = pools.submit(runnable);//默认第二个参数是null，所以返回给future装载的值也是null
        try {
            //若子线程还未执行完，主线程调用future.get方法时会阻塞，直到子线程执行完后填充future才被唤醒
            System.out.println(Thread.currentThread().getName()+" :f: "+f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //CheckedExption---submit方法用法二:接收Runnable参数和T result,result是传入被操作的引用参数，通过future可以在调用线程（即主线程）中窥探该参数的值变化，相当于得到操作结果了
        final String[] paramResult = new String[2];
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    paramResult[0]="0=>"+Thread.currentThread().getName();
                    throw new Exception(" runnable的run里的异常只能被内部捕获，抛不出去");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName()+e.getMessage());
                }
            }
        };
        Future<String[]> f2 = pools.submit(runnable2,paramResult);
        try {
            System.out.println(Thread.currentThread().getName()+" :f2: "+f2.get()[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //CheckedExption---submit方法用法三:接收Callable参数
        Future<String> future = pools.submit(callable);//快捷键自动生成变量声明,command+alt+v
        try {
            future.get();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"通过future.get()捕获了callable所在线程中断异常:"+e.getMessage());
//            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(Thread.currentThread().getName()+"通过future.get()捕获了callable的执行代码异常:"+e.getMessage());
//            e.printStackTrace();
        }

        //execute方法,只接收Runnable参数，也没有返回值，捕捉不到异常
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"使用execute方法");
        pools.execute(runnable);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------不可检查到的运行时异常（UnCheckedException）案例：-------------");
        //UnCheckedExption---execute方法
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                int a = 100,b=0;
                double r = a / b;
                System.out.println(Thread.currentThread().getName()+" r="+r);
            }
        };
        try {
            pools.execute(runnable3);
        }catch (Exception e ){
            System.out.println("计算出bug拉:"+e.getMessage());
        }

        Thread.sleep(1000);
        //UnCheckedExption---submit方法
        Future<?> future1 = pools.submit(runnable3);
        try {
            future1.get();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"通过future.get()捕获了callable所在线程中断异常:"+e.getMessage());
//            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(Thread.currentThread().getName()+"通过future.get()捕获了callable的执行代码异常:"+e.getMessage());
//            e.printStackTrace();
        }
        pools.shutdown();
    }
}
