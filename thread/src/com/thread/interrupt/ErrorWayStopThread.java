package com.thread.interrupt;

/**
 * 使用stop方法停止线程是错误的方式，强制线程立即停止，犹如直接拔电源关台式电脑，容易使单位任务执行到中途就提前结束，造成不可复查的数据丢失
 * */
public class ErrorWayStopThread implements Runnable {

    public static void main(String[] args) {
        Runnable runanble = new ErrorWayStopThread();
        Thread thread = new Thread(runanble);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.interrupt();
        thread.stop();
        System.out.println("结束!!!");

    }

    @Override
    public void run() {
        //模拟5台台式电脑都写10TB文件
        for (int i = 1; i <= 5; i++) {
//            System.out.println("");
            System.out.print("\r\n电脑"+i+"开始工作");
            for (int j = 1; j <= 10; j++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("写完"+j+"TB,");
            }
            System.out.print("任务完成\r\n");
        }
    }
}
