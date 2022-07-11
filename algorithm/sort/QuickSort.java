package algorithm.sort;
/**
 * 快速排序：
 * 分治思想的运用：1.每次排序时，选择数组项中某一个数作为基准数，
 *              2.将数组中小于等于基准数的所有元素放到数组左边，大于基准数的放到数组右边，分成两个子数组，
 *              3.递归操作分化出来的两个子数组，重复1、2步骤
 *              4.当数组项只有一项时结束递归，也就结束排序
 *              5.分化数组的算法：
 *                  定义一个变量j表示分化的左子数组最后一位，也就是分化数组的界线，
 *                  将数组第一项作为基准数，从第二项开始遍历数组项，
 *                  当前元素大于基准数时，直接继续下一元素，
 *                  当前元素小于等于基准数时，将当前元素与j+1位置元素进行交换，交换完后j++，
 *                  遍历完数组后，j位置就是基准数最后排序结果的位置
 * 时间复杂度：最好时是O(nLogn)
 *           最差时是O(n^2),当数组是有序时并且基准数是固定规则的数时
 * */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{11,18,5,1,2,8,3,9,6};
        int[] arr = new int[]{9,8,7,6,5,0,1,2};
        sortArr(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    static void sortArr(int[] arr){
        sortByQuick(arr,0,arr.length-1);
    }

    //默认以数组项第一项作为基准数
    static void sortByQuick(int[] arr,int start,int end){
        int count = end - start+1;
        if(count<=1){
            return;
        }

        //分化数组，小于等于基准数的左边，大于的放右边
        int baseIndex = partition(arr,start,end);//分化后基准数的新位置，即排序后该基准数在结果数组中的位置
        //递归左边的数组
        sortByQuick(arr,start,baseIndex-1);
        //递归右边的数组
        sortByQuick(arr,baseIndex+1,end);
    }

    static int partition(int[] arr,int start,int end){
        int base = arr[start];
        int j=start;//当前分化出小于等于基准数的子数组最后一位
        for (int i = start+1; i <= end; i++) {
            if(arr[i]<=base){
                //将j+1位置的元素和当前元素交换
                int tmp = arr[j+1];
                arr[j+1] = arr[i];
                arr[i] = tmp;
                j++;
            }
        }
        int t = arr[j];
        arr[j] = arr[start];
        arr[start] = t;
        return j;
    }


}
