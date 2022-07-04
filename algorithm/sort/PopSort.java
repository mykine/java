package algorithm.sort;

/**
 * 冒泡排序-相邻的元素依次比较，前面的比后面的大就交换，直到最大的冒泡到最后面，依次类推，第二大的冒泡到倒数第二个位置
 * 时间复杂度：O(n^2)
 * */
public class PopSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5,3,1,7,9,8};
        System.out.println("after sort arr is:");
        sortAsc(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    static void sortAsc(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}
