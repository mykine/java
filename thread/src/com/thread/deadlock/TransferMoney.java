package com.thread.deadlock;

import java.util.Date;

/**
 * 相互转账造成死锁
 * */
public class TransferMoney implements  Runnable{

    int flag = 1;

    /**
     * 静态变量是属于类的，JVM加载类时就分配内存，无需实例化，是实例化独享共享的，
     * 通过类.静态成员可以直接访问，对象只能间接通过在普通方法中操作this.静态变量来实现外部访问
     */
    public static int flagS = 1;

    static Account a = new Account("a",1000);
    static Account b = new Account("b",1000);

    public static void main(String[] args) throws InterruptedException {
        TransferMoney.flagS = 2;
        TransferMoney r1 = new TransferMoney();
//        System.out.println("r1.flag="+ r1.getFlag());
        r1.flag = 1;
        TransferMoney r2 = new TransferMoney();
        r2.flag = 2;
//        System.out.println("r2.flag="+ r2.getFlag());
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
        synchronized (from){
            //可能造成死锁
            System.out.println(Thread.currentThread().getName()+"拿到锁"+from.name);
            synchronized(to){
                System.out.println(Thread.currentThread().getName()+"拿到锁"+to.name);
                 if((from.balance-num)<0){
                     System.out.println(from.toString()+"余额不足");
                 }else{
                     from.balance-=num;
                     to.balance+=num;
                     System.out.println(Thread.currentThread().getName()+new Date() +"转账"+num+"成功!");
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


    public int getFlag(){
        return this.flagS;
    }

    public void setFlag(int f){
        this.flagS=f;
    }

}
