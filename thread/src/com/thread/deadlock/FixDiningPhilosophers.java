package com.thread.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 修复哲学家就餐造成的死锁-避免策略-改变其中1个哲学家拿筷子的顺序
 * */
public class FixDiningPhilosophers {

    public static void main(String[] args) {

        //5根筷子
        Chopstick[] chopsticks = new Chopstick[5];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Chopstick();
            chopsticks[i].name = "筷子"+(i+1);
        }

        //5个哲学家
        Philosopher[] persons = new Philosopher[5];
        for (int i = 0; i < persons.length; i++) {
            Chopstick leftChopstick = chopsticks[i];
            Chopstick rightChopstick = chopsticks[(i+1)%chopsticks.length];
            if(i==(persons.length-1)){
                //最后一个哲学家拿筷子顺序与其他人相反，可避免死锁
                persons[i] = new Philosopher(leftChopstick,rightChopstick);
            }else{
                persons[i] = new Philosopher(rightChopstick,leftChopstick);
            }
            Thread thread = new Thread(persons[i],"哲学家"+(i+1));
            thread.start();
        }

        //监测死锁
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockThreadIds = threadMXBean.findDeadlockedThreads();
        if( deadlockThreadIds!=null && deadlockThreadIds.length>0 ){
            System.out.println("监测到死锁");
            for (int i = 0; i < deadlockThreadIds.length; i++) {
                ThreadInfo deadlockThreadinfo= threadMXBean.getThreadInfo(deadlockThreadIds[i]);
                System.out.println(deadlockThreadinfo.getThreadName()+"-"+deadlockThreadinfo.getLockName());
            }
        }

    }

    //筷子
    static class Chopstick{
        public String name;
    }

    //哲学家
   static class Philosopher implements  Runnable{

        private Chopstick leftChopstick;//左筷子
        private Chopstick rightChopstick;//右筷子

        public Philosopher(Chopstick leftChopstick,Chopstick rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        //动作
        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName()+action);
            //花费点时间
//            Thread.sleep((long)(Math.random()*10));
        }

        @Override
        public void run() {
            try {
                while (true){
                    //思考
                    doAction("思考问题,饿了~");
                    synchronized (this.leftChopstick){
                        //抢夺左筷子
                        doAction("拿到左筷子-"+this.leftChopstick.name);
                        synchronized (this.rightChopstick){
                            //抢夺右筷子
                            doAction("拿到右筷子-"+this.rightChopstick.name);
                            //吃饭
                            doAction("用筷子吃饭,吃饱了～");
                        }
                        //释放右筷子
                        doAction("放下右筷子-"+this.rightChopstick.name);
                    }
                    //释放左筷子
                    doAction("放下左筷子-"+this.leftChopstick.name);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
