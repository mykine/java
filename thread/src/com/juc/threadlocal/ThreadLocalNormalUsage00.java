package com.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 两个线程打印日期
 * */
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(new ThreadLocalNormalUsage00().date(10));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(new ThreadLocalNormalUsage00().date(1007));
            }
        }).start();
    }

    public String date(int seconds){

        Long millseconds = seconds*1000L;
        //参数单位是毫秒,从1970.1.1 00:00:00 GMT计时(对应我国东八区的是1970.1.1 08:00:00)
        Date d = new Date(millseconds);

        String formatStr = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(d);
    }
}
