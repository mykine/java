package algorithm.heappriorityqueue;

/**
 * 简单的堆排序
 * 算法：1.利用堆的入队方法元素入队
 *      2.不断地利用堆的出队方法顺序获取元素，实现排序效果
 * */
public class HeapSort {


    public static void main(String[] args) {
        int[] dataArr = new int[]{3,1,2,5,4,9,8,6,7};
        //利用最大堆将dataArr中的元素从小到大排序
        BigHeap bigHeap = new BigHeap();
        for (int i=0;i< dataArr.length;i++){
            bigHeap.push(dataArr[i]);
        }
        System.out.println("层级打印堆:");
        bigHeap.printDeap();
        for (int i = dataArr.length-1; i >=0 ; i--) {
            dataArr[i] = bigHeap.poll();
        }
        System.out.println("从小到大排序后数据为：");
        for (int i = 0; i < dataArr.length; i++) {
            System.out.print(dataArr[i]+",");
        }
    }

}
