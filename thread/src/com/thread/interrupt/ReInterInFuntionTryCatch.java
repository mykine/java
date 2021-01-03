package com.thread.interrupt;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 最佳实践2：在被run调用的方法中catch了InterruptedException之后调用Thread.currentThread().interrupt()来恢复设置中断状态，
 * 以便在后续的执行中，依然能够检查到刚才发生的中断状态,通过Thread.currentThread().isInterrrupted()来检查
 * */
public class ReInterInFuntionTryCatch {

    public static void main(String[] args) throws InterruptedException {

        Runnable rannable = ()->{
            int num=0;
            while (num<100){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("监控报警提示:"+Thread.currentThread().getName()+"执行出现异常");
                    break;
                }
                num++;
                reInterrTryExceptionInFunc(num);
            }
        };

        Thread thread = new Thread(rannable);
        thread.start();
        Thread.sleep(10);
        thread.interrupt();
        System.out.println("执行完毕:"+Thread.currentThread().getName());
    }

    /**
     * 在方法内try-catch异常，并且捕获到后进行恢复中断状态(重新执行下interrupt()函数)
     * */
    private static void reInterrTryExceptionInFunc(int num){
        int randomInt = ThreadLocalRandom.current().nextInt(1,3);
        if(1==randomInt){
            System.out.println(Thread.currentThread().getName()+":"+num+"-执行休眠-"+randomInt);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        System.out.println(num+"执行成功"+randomInt);


    }
}

