package algorithm.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划；例题
 * 问题：有一个楼梯，总共N阶个台阶，每次可以爬1个或2个台阶，请问，爬上这样一个楼梯，有多少种爬法？
 * 思路：分解问题，从上到下枚举一些子操作，找寻规律，确定最优通用的子操作得到计算公式
 *           n阶
 *   n-1阶         n-2阶
 * n-2阶 n-3阶  n-3阶  n-4阶
 * ... ...
 * 可发现 通用子操作是f(n)=f(n-1)+f(n-2)，类似斐波那契数列
 * 不过，这里的f(0)=0,f(1)=1,f(2)=2
 *  然后，从下到上累计执行通用最优子操作，实现动态规划算法
 *  所以从这里可以理解动态规划其实是指：被计算的目标的值一直在动态的变化，每个值计算的结果要被规划到未来被使用，
 *  从题目的解法上讲，就是变量n不断变化，每个n值的结果都会被在后面n值计算所使用到
 * */
public class ClimbStairs {

    public static void main(String[] args){
        for (int n = 1; n <= 10; n++) {
            System.out.println(n+"阶台阶走法种数:"+climbStairs(n));
        }
    }

    public static long  climbStairs(int n){
        Map<Integer,Long> map = new HashMap<>();
        for(int i=1;i<=n;i++){
            if(1==i){
                map.put(i,1L);
            }else if(2==i){
                map.put(i,2L);
            }else{
                map.put(i,map.get(i-1)+map.get(i-2));
            }
        }
        return map.get(n);
    }

}
