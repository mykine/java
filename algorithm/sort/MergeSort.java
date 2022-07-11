package algorithm.sort;
/**
 * 归并排序：
 * 分治思想的运用：首先，从顶向下递归，将数组切分成两半，递归此操作，直到切分的子数组足够小(数组元素只有一项)为止，这一步骤的时间复杂度是O(logN)
 *              然后，从底往上回溯，对回溯的两个子数组做归并排序操作，这一步骤的时间复杂度是O(N)
 *              总的时间复杂度是O(nlogN)
 *  注意：这里的数组切分成2个子数组，是逻辑上的，通过数组中间位置分隔成arr[0...mid]、arr[mid+1,...arr.length-1]这种两个逻辑子数组；
 *       从递归到最底部的子数组开始排序合并后形成的回溯子数组都是有序数组，因此可以使用归并排序；
 * */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{11,18,5,1,2,8,3,9,6};
        sortArr(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" "+arr[i]);
        }
    }

    static void sortArr(int[] arr){
        sortMerge(arr,0,arr.length-1);
    }

    static void sortMerge(int[] arr,int start,int end){
        if(start>=end){
            return;
        }
        int mid = (start+end)/2;//如果数组特别大，这个值可能超过int最大值，这是归并排序的一个隐患
        sortMerge(arr,start,mid);
        sortMerge(arr,mid+1,end);
        execSortMerge(arr,start,mid,end);
    }

    static void execSortMerge(int[] arr,int start,int mid,int end){
        //归并两个有序数组成一个最终有序数组，需要格外的一个开辟临时数组来操作，这也是归并排序的一个弊端
        int count = end - start +1;
        int[] tmpArr = new int[count];
        //把原数组目标元素拷贝到临时数组中，临时数组作为被操作的数组，原数组目标区域被清空作为被填充结果数组
        for (int i = start; i <= end; i++) {
            tmpArr[i-start] = arr[i];
        }
        //原数组中子数组起止位置映射到临时数组的起止位置
        int startInTmpArr = 0;
        int midInTmpArr = mid-start+0;
        int endInEndArr = end-start+0;
        int m = startInTmpArr,n = midInTmpArr+1;
        int resultStart = start;//在结果区域用于填充数据的指针
        while (m<=midInTmpArr&&n<=endInEndArr){
            if(tmpArr[m]<tmpArr[n]){
                arr[resultStart] = tmpArr[m];
                m++;
            }else{
                arr[resultStart] = tmpArr[n];
                n++;
            }
            resultStart++;
        }
        //处理剩余未比较到的元素
        while (m<=midInTmpArr){
            arr[resultStart] = tmpArr[m];
            m++;
            resultStart++;
        }
        while (n<=endInEndArr){
            arr[resultStart] = tmpArr[n];
            n++;
            resultStart++;
        }

    }


}
