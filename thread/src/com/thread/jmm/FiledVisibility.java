package com.thread.jmm;

/**
 * 演示可见性问题
 * */
public class FiledVisibility {
    int a=1,b=2;

    private void change(){
        a=3;
        b=a;
    }

    private void print(){
        System.out.println("a="+a+",b="+b);
    }

    public static void main(String[] args) {

        while (true){

            FiledVisibility obj = new FiledVisibility();

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    obj.change();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    obj.print();
                }
            });

            thread1.start();
            thread2.start();

        }

    }

}
