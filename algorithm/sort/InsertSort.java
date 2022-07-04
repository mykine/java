package algorithm.sort;

/**
 * 插入排序-为当前操作的元素找到数组中合适的位置-时间复杂度O(n^2)
 * 算法思路：依次遍历数组每一个元素，当操作该元素时，将它与前面的元素进行比较，比前面的小时就与前面的元素进行交换，直到前面的比它还小或到了尽头，就确定了插入的位置
 *
 * */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,1,7,8,6,10,9};
        System.out.println("arr after sort is");
        sortAsc(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    static void sortAsc(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i-1; j >= 0; j--) {
                if(arr[minIndex]<arr[j]){
                    int tmp = arr[minIndex];
                    arr[minIndex] = arr[j];
                    arr[j] = tmp;
                    minIndex = j;
                }else {
                    continue;
                }
            }
        }
    }
}
