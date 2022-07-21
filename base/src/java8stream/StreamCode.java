package base.src.java8stream;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 *
 * java8引入了lombda表达式匿名函数编程
 * stream流作为数据源进入管道操作
 */
public class StreamCode {

    public static void main(String[] args) {
        Stream.of(1,5,2,6,4,3)
                .map(x->x*x)
                .map(x->x.toString())
                .forEach(x->{
                    System.out.println(x);
                });

        //一百万个随机数的集合求最大元素
        Random r = new Random();
        List<Integer> list = IntStream.range(0, 1_000_000)
                .map(x -> r.nextInt(10_000_000))
                .boxed()
                .collect(Collectors.toList());
        //串行流求最大值
        long beginTime1 = System.currentTimeMillis();
        Optional<Integer> max1 = list
                .stream()
                .max((a, b) -> a - b);
        long endTime1 = System.currentTimeMillis();
        System.out.println("串行流执行: max1="+max1+",耗时:"+(endTime1-beginTime1)+" ms");

        /**
         * 并行流求最大值，并行流默认使用CPU核数-1个线程来执行任务，
         * 但是这个业务的max功能简单不耗时，而切换线程上下文和多线程对百万元素的切分操作以及对结果合并操作比max操作耗时的多，
         * 所以这里的并行执行反而耗时比串行的耗时
         * */
        System.out.println("CPU核数："+Runtime.getRuntime().availableProcessors());
        long beginTime2 = System.currentTimeMillis();
        Optional<Integer> max2 = list
                .parallelStream()
                .max((a, b) -> a - b);
        long endTime2 = System.currentTimeMillis();
        System.out.println("并行流执行: max2="+max2+",耗时:"+(endTime2-beginTime2)+" ms");



    }

}
