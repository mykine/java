package algorithm.sort;
/**
 * 选择排序-为指定位置选择最合适的元素-时间复杂度O(n^2)
 * 算法思路：为数组第一个位置找到整个数组最小的元素与当前第一个位置的元素交换，
 *         为数组第二个位置找到整个数组第二小的元素与当前第二个位置的元素交换，
 *         依次类推，直到整个数组的所有位置找到对应合适的元素，完成选择排序
 * */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3,1,6,7,5,9,8};
        sortAscArr(arr);
        System.out.println( "after sort arr is:" );
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    static void sortAscArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]){
                    minIndex = j;
                }
            }
            if( minIndex != i ){
                int tmp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
