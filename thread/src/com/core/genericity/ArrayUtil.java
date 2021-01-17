package com.core.genericity;

import java.util.GregorianCalendar;

public class ArrayUtil {

    /**
     * 泛型方法:求数组中的最大最小值
     * <T extends Comparable>规定了泛型类型的约束，必须继承了Comparable
     * Pair<T>是返回值
     * T[] args是泛型参数
     * */
    public static <T extends Comparable> Pair<T> minMax(T[] args) {
        if(null==args ||0==args.length)
        {
            return null;
        }
        T max=args[0];
        T min=args[0];
        for (int i = 0; i < args.length; i++) {
            max = args[i].compareTo(max)>0 ? args[i] : max;
            min = args[i].compareTo(min)<0 ? args[i] : min;
        }
        return new Pair<T>(min,max);
    }

    /**
     * 泛型方法:求数组平均值
     * <T>是泛型类型的约束,默认没有约束
     * void是返回值
     * T[] args是泛型参数
     * */
    public static <T> void avg(T[] args) {

    }

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{1,5,2,8,9,6};
        System.out.println("arr1的最大最小是:"+ArrayUtil.minMax(arr1));

        Long[] arr2 = new Long[]{2L,6L,12L,8L,9L};
        System.out.println("arr2的最大最小是:"+ArrayUtil.minMax(arr2));
    }
}
