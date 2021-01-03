package com.thread.interrupt;

/**
 * 最佳实践:catch了InterruptedException之后的有限选择：在循环中被调用的方法签名时抛出异常
 * */
public class FuntionSignThrowException {

    public static void main(String[] args) throws InterruptedException {

        Runnable rannable = ()->{
            int num=0;
            while (num<100){
                num++;
                //Rannable的run方法不能在签名时抛异常,所以在逻辑代码中try-catch
                try {
                    throwExceptionInFunc(num);
                } catch (Exception e) {
                    System.out.println("监控报警提示:"+Thread.currentThread().getName()+"执行出现异常");
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(rannable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

    }

    /**
     * 在方法签名时抛出异常,方便上层方法捕获处理
     * */
    private static void throwExceptionInFunc(int num) throws Exception {
//        int randomInt = ThreadLocalRandom.current().nextInt(1,3);
//        if(1==randomInt){
//            System.out.println("执行出现异常了");
//            throw new Exception();
//        }
//        System.out.println(num+"执行成功!");

        System.out.println(Thread.currentThread().getName()+"执行休眠"+num);
        Thread.sleep(10);//会抛出异常java.lang.InterruptedException: sleep interrupted
    }
}

