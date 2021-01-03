package uncaughtexception;

import java.util.concurrent.TimeUnit;

/**
 * 1.不加try catch抛出4个异常，都带线程名字
 * 2. 加了try catch，期望捕获到第一个线程的异常，线程234不应该运行，希望看到打印出Caught Exception
 * 3. 执行时发现，根本没有Caught Exception,线程234依然运行并且抛出异常
 *
 * 说明子线程的异常不能用传统的方法捕获
 * */
public class CantCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        try{
            new Thread(new CantCatchDirectly(),"线程1").start();
            TimeUnit.SECONDS.sleep(1);
            new Thread(new CantCatchDirectly(),"线程2").start();
            TimeUnit.SECONDS.sleep(1);
            new Thread(new CantCatchDirectly(),"线程3").start();
            TimeUnit.SECONDS.sleep(1);
            new Thread(new CantCatchDirectly(),"线程4").start();
        }catch (RuntimeException e){
            //无效代码，因为传统的try-catch方式无法捕获子线程抛出的异常
            System.out.println("捕获子线程异常");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
