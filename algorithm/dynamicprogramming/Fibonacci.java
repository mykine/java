package algorithm.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 从斐波纳契数列的编程 递归解法 演变成 动态规划 解法
 *  传统的斐波那契数列递归解法是自上往下的解法，穷举出所有操作的话，就像一颗树状结构，每个节点就是一个子操作，
 *  利用记忆化搜索优化重复子操作的执行次数的从上往下的递归解法，可以大幅提高性能，
 *  动态规划，也是一种利用了记忆化搜索的解法，只不过，动态规划是从下往上的解法：
 *      通过抽象出重复字操作，并且保证就是最优的子操作，再利用记忆化搜索，实现从下往上的解法
 *
 * */
public class Fibonacci {
    private Map<Integer,Long> mapFib = new HashMap<>();
    private long execCount = 0;//斐波那契函数执行次数
    public static void main(String[] args) {
        System.out.println("---------------- 普通递归求解斐波纳契数列----------------------");
        int n =40;
        Fibonacci fibonacci = new Fibonacci();
        long beginTime = System.currentTimeMillis();
        long res1 = fibonacci.fibonacci(n);
        long endTime = System.currentTimeMillis();
        System.out.println("res1="+res1);
        System.out.println("cost Time:"+(endTime-beginTime)+" ms,execCount="+fibonacci.execCount);
        fibonacci.execCount = 0;

        System.out.println("----------------利用记忆化搜索优化递归求解斐波纳契数列----------------------");
        long beginTime2 = System.currentTimeMillis();
        long res2 = fibonacci.fibonacci2(n);
        long endTime2 = System.currentTimeMillis();
        System.out.println("res2="+res2);
        System.out.println("cost Time:"+(endTime2-beginTime2)+" ms,execCount="+fibonacci.execCount);
        fibonacci.execCount = 0;

        System.out.println("---------------使用从下往上穷举子操作的动态规划算法求解斐波纳契数列-----------------------");
        long beginTime3 = System.currentTimeMillis();
        long res3 = fibonacci.dynamicProgramming(n);
        long endTime3 = System.currentTimeMillis();
        System.out.println("res3="+res3);
        System.out.println("cost Time:"+(endTime3-beginTime3)+" ms,execCount="+fibonacci.execCount);

    }

    /**
     * 传统递归求解斐波纳契数列
     * 从第0个数是f(0)=0，第1个数是f(1)=1，第2个数是f(2)=1，第n个数是f(n)=f(n-2)+f(n-1),
     *     0
     *    1 1
     *   2 3 5
     * 8 13 21 34
     * */
   long fibonacci(int n){
       execCount++;
       if(1==n||2==n){
        return 1;
       }
       return fibonacci(n-1)+fibonacci(n-2);
   }

    /**
     * 利用记忆化搜索优化求解斐波纳契数列
     * */
    long fibonacci2(int n){
        if(mapFib.containsKey(n)){
            return mapFib.get(n);
        }
        execCount++;
        if(1==n||2==n){
            mapFib.put(n,1L);
            return 1;
        }
        long res = fibonacci2(n-1)+fibonacci2(n-2);
        mapFib.put(n,res);
        return res;
    }

    /**
     * 利用动态规划算法求解斐波纳契数列
     * */
    long dynamicProgramming(int n){
        Map<Integer,Long> map = new HashMap<>();
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if(1==i || 2==i){
                res=1;//分解出的最初子操作
            }else{
                res = map.get(i-1)+map.get(i-2);//分出的通用子操作
            }
            map.put(i,res);
            execCount++;
        }
        return res;
    }

}
