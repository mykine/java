package com.thread.deadlock;

import java.util.Date;

/**
 * 修复死锁策略1：避免策略-哲学家就餐的还手策略-转账换序方案
 * */
public class FixDeadLock1 implements  Runnable{

    int flag = 1;


    static Account a = new Account("a",1000);
    static Account b = new Account("b",1000);
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        FixDeadLock1 r1 = new FixDeadLock1();
        FixDeadLock1 r2 = new FixDeadLock1();
//        System.out.println("a.hashCode="+ a.hashCode());
//        System.out.println("a.hashCode="+ System.identityHashCode(a));
        r1.flag = 1;
        r2.flag = 2;

//        System.out.println("b.hashCode="+ b.hashCode());
//        System.out.println("b.hashCode="+ System.identityHashCode(b));

        Thread ThreadAB = new Thread(r1);
        Thread ThreadBA = new Thread(r2);
        ThreadAB.start();
        ThreadBA.start();
        ThreadAB.join();
        ThreadBA.join();
        System.out.println("a余额="+a.balance+",b的余额="+b.balance);
    }

    @Override
    public void run() {
        if( 1 == flag ){
            transfer(a,b,100);
        }else{
            transfer(b,a,50);
        }
    }

    public static void transfer(Account from, Account to, int num) {

        //执行转账动作的辅助类
        class Helper{

            public void tranferExc(){
                if((from.balance-num)<0){
                    System.out.println(from.toString()+"余额不足");
                }else{
                    from.balance-=num;
                    to.balance+=num;
                    System.out.println(Thread.currentThread().getName()+new Date() +"转账"+num+"成功!");
                }
            }

        }

        //根据操作对象的编号大小定义加锁顺序(也可以利用数据库的id自增唯一值来排序,避免hashCode冲突的情况)
        int fromCode = from.hashCode();
        int toCode = System.identityHashCode(to);

        if( fromCode > toCode ){
            synchronized (from){
                System.out.println(Thread.currentThread().getName()+"拿到锁"+from.name);
                synchronized(to){
                    System.out.println(Thread.currentThread().getName()+"拿到锁"+to.name);
                    new Helper().tranferExc();
                }
            }
        }else if(toCode>fromCode){
            synchronized (to){
                System.out.println(Thread.currentThread().getName()+"拿到锁"+from.name);
                synchronized(from){
                    System.out.println(Thread.currentThread().getName()+"拿到锁"+to.name);
                    new Helper().tranferExc();
                }
            }
        }else{
            //hashCode冲突时额外的锁
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"拿到lock锁");
                synchronized (from){
                    System.out.println(Thread.currentThread().getName()+"拿到锁"+from.name);
                    synchronized(to){
                        System.out.println(Thread.currentThread().getName()+"拿到锁"+to.name);
                        new Helper().tranferExc();
                    }
                }
            }
        }


    }

    static class Account {
        public String name;
        public int balance;

        public Account(String name,int balance) {
            this.name = name;
            this.balance = balance;
        }
    }

}
