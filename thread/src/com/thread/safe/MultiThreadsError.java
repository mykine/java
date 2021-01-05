package com.thread.safe;

/**
 * 观察者模式,注册监听器造成的线程安全问题
 * */
public class MultiThreadsError {

    int count;

    public MultiThreadsError(MySource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                System.out.println("\ncount="+count);
            }
        });
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count=100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mySource.eventCome(new Event() {});
            }
        }).start();

        MultiThreadsError multiThreadsError = new MultiThreadsError(mySource);
    }

    static class MySource{
        private EventListener listener;

        void registerListener(
                EventListener eventListener
        ){
            this.listener = eventListener;
        }

        void eventCome(Event e){
            if(listener != null){
                listener.onEvent(e);
            }else{
                System.out.println("还未完成初始化");
            }
        }
    }

    interface EventListener{
        void onEvent(Event e);
    }

    interface Event{}
}
