package uncaughtexception;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseOwnUncaughtExceptionHandler implements Runnable {
    public static void main(String[] args) throws InterruptedException {

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(),"线程1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程2").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程3").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new UseOwnUncaughtExceptionHandler(),"线程4").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
