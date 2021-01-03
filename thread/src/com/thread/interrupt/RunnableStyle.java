package com.thread.interrupt;

public class RunnableStyle  implements  Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("Runnable线程");
    }
}

