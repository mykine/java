package algorithm.heappriorityqueue;

/**
 * 优先队列：跟普通队列一样有入队、出队基本功能，但是出队不是按照传统的先进先出规则，而是按照优先级出队
 * 应用场景：适合需要动态实时排序的场景，比如医院源源不断来的患者优先就医队列、游戏中防御塔自动攻击塔下敌方英雄仇恨值目标队列
 * 实现方式：优先队列是通过堆这种数据结构来实现的
 * 堆：常见的有二叉堆，分为最大堆、最小堆，是一种树形数据结构，特点是：
 *     1.最大二叉堆：属于完全二叉树，并且父级节点不小于所有子孙节点
 *     2.最小二叉堆：属于完全二叉树，并且父级节点不大于所有子孙节点
 * 完全二叉树：除最底层叶子节点那一层之外，该二叉树所有层都是满节点，并且叶子节点都靠左侧集中，不会存在只有右孩子的情况
 * 堆的物理存储实现方式：可以使用数组存储，数组下标0不存储数据，从根节点对应数组下标1开始依次存储节点信息，就可以套用公式:
 *      设根节点序号是1对应数组下标1，二叉堆从上到下、从左往右数节点序号n对应数组下标n
 *      n节点的左孩子序号是2*n，右孩子序号是2*n+1
 *      n节点的父节点序号是n/2取整
 * 非叶子节点特性：含有num个节点的二叉堆，从上到下、从左往右数节点序号为(num/2)的节点是最后一个非叶子节点
 * 最大堆入队算法-shiftUp向上交换法-时间复杂度O(nlogn)：
 *      1.设最大堆的节点个数是count初始值是0，物理存储数据的数组是arr,arr[0]不存储数据，从根节点开始往arr[1]开始存数据
 *      2.新元素n入队，先直接将n存储到数组中空缺位置arr[count]，count++
 *      3.此时n的序号就是数组索引count值，堆可能不满足最大堆性质，需要将新元素与父级元素进行层层替换，直到满足最大堆性质为止
 *      4.它的父节点序号是(count/2),与父节点比大小，如果比父节点大，就进行交换，层层比较，直到与根节点比较完为止
 *
 * 最大堆出队算法-shiftDown向下交换法-时间复杂度O(nlogn)：
 * */
public class BigHeap {

    private int[] dataArr = new int[100];
    private int count=0;


    public static void main(String[] args) {
        BigHeap heap = new BigHeap();
//        heap.push(1);
//        heap.push(2);
//        heap.push(3);
//        heap.push(4);
//        heap.push(5);
//        heap.push(6);
//        heap.push(7);
//        heap.push(8);
//        heap.push(9);
        int[] paramArr = new int[]{1,2,3,4,5,6,7,8,9};
        heap.headpify(paramArr);
        System.out.println("最大堆：");
        heap.printDeap();
        System.out.println("出队后：");
        heap.poll();
        heap.printDeap();
    }

    /**
     * Heapify算法是指将一个数组直接转换为堆存储的算法，
     * 算法思想：从最后一个非叶子节点开始执行shiftDown，直到所有非叶子节点代表的子树均为最大堆即完成最大堆化
     *         元素个数为num的数组，元素从数组下标1开始存储，对应的完全二叉树最后一个非叶子节点序号是num/2,
     * 最大堆的构造性能对比：
     * 1.利用shiftUp一个一个入队，时间复杂度是O(nlogn)
     * 2.使用Headpify算法一次性将整个数组最大堆化，时间复杂度是O(n)
     * */
    public void headpify(int[] paramArr){
        //先一次性填充到存储空间中
        for (int i = 0; i < paramArr.length; i++) {
            dataArr[i+1] = paramArr[i];
            count++;
        }
        //然后从后往前依次将非叶子节点的子树最大堆化
        int sunRoot = paramArr.length / 2;
        while (sunRoot>=1){
            shiftDown(sunRoot);
            sunRoot--;
        }
    }

    /**
     * 添加新元素
     * */
    public void push(int n){
        count++;
        dataArr[count] = n;
        shiftUp(count);
    }

    /**
     * 添加新元素后调整堆为最大堆
     * 算法逻辑：新元素是直接插入在数组最末尾处，此时堆可能不满足最大堆性质，需要将新元素与父级元素进行层层替换，直到满足最大堆性质为止
     * */
    private void shiftUp(int indexN){
        while (indexN>1){
            int tmp = dataArr[indexN/2];//找到父节点
            if(tmp<dataArr[indexN]){
                dataArr[indexN/2] = dataArr[indexN];
                dataArr[indexN] = tmp;
                indexN = indexN / 2 ;
            }else {
                break;//已经达到了最大堆性质，直接停止操作
            }
        }
    }

    public boolean isEmpty(){
        return count<=0 ? true : false;
    }

    /**
     * 出队
     * */
    public int poll(){
        if(count<=0){
            return -1;//-1表示空队列
        }
        int res = dataArr[1];
        //将最后一个元素放到堆顶位置，保持完全二叉树性质
        dataArr[1] = dataArr[count];
        count--;
        //然后通过shiftDown算法调整整体元素排序为最大堆性质
        shiftDown(1);
        return res;
    }

    /**
     * 从堆顶开始调整元素排序，再次成为最大堆
     * 算法逻辑：从堆顶开始，与左右孩子中最大的那个进行比较替换，一直向下操作，直到叶子节点或直接满足最大堆条件为止
     * */
    private void shiftDown(int index){

        while (index <= count){
            //与孩子中比较大的那个进行交换，形成最大堆的性质
            int leftIndex = index*2;
            int rightIndex = index*2+1;
            if(leftIndex > count && rightIndex > count){
                break;//已经形成最大堆形态，停止操作
            }else if(leftIndex <= count && rightIndex > count){
                if(dataArr[leftIndex]>dataArr[index]){
                    int tmp = dataArr[leftIndex];
                    dataArr[leftIndex] = dataArr[index];
                    dataArr[index] = tmp;
                    index = leftIndex;
                }else{
                    break;//已经形成最大堆形态，停止操作
                }
            }else if(rightIndex <= count && leftIndex > count) {
                if(dataArr[rightIndex]>dataArr[index]){
                    int tmp = dataArr[rightIndex];
                    dataArr[rightIndex] = dataArr[index];
                    dataArr[index] = tmp;
                    index = rightIndex;
                }else{
                    break;//已经形成最大堆形态，停止操作
                }
            }else{
                if( dataArr[leftIndex] >= dataArr[rightIndex] && dataArr[leftIndex] > dataArr[index] ){
                    int tmp = dataArr[leftIndex];
                    dataArr[leftIndex] = dataArr[index];
                    dataArr[index] = tmp;
                    index = leftIndex;
                }else if(dataArr[rightIndex] >= dataArr[leftIndex] && dataArr[rightIndex] > dataArr[index]){
                    int tmp = dataArr[rightIndex];
                    dataArr[rightIndex] = dataArr[index];
                    dataArr[index] = tmp;
                    index = rightIndex;
                }else{
                    break;//已经形成最大堆形态，停止操作
                }
            }
        }
    }

    /**
     * 打印堆-层级打印
     * 使用连环双队列，交替存储打印每一层节点以及孩子节点，时间复杂度O(nlogN)
     *
     * */
    public   void printDeap(){
        BaseQueue queue1 = new BaseQueue();
        BaseQueue queue2 = new BaseQueue();
        queue1.offer(1);//堆的根节点从数组项1开始

        while ( queue1.size() > 0 || queue2.size() > 0 ){
            if(queue1.size()>0){
                while (queue1.size()>0){
                    int index = queue1.poll();
                    System.out.print(dataArr[index]+" ");
                    int leftIndex = index*2;
                    int rightIndex = index*2+1;
                    if(leftIndex <= count){
                        queue2.offer(leftIndex);
                    }
                    if(rightIndex <= count){
                        queue2.offer(rightIndex);
                    }
                }
            }else{
                while (queue2.size()>0){
                    int index = queue2.poll();
                    System.out.print(dataArr[index]+" ");
                    int leftIndex = index*2;
                    int rightIndex = index*2+1;
                    if(leftIndex <= count){
                        queue1.offer(leftIndex);
                    }
                    if(rightIndex <= count){
                        queue1.offer(rightIndex);
                    }
                }
            }
            System.out.println("");
        }

    }

    /**
     * 自定义队列基础数据结构-数组实现
     * */
    static class BaseQueue{

        private int count = 0;//队列元素个数
        private int front = 0;//队列头部数组下标
        private int tail = 0;//队列尾部数组下标
        private int[] elements = new int[100];

        public BaseQueue(){
            for (int i = 0; i < elements.length; i++) {
                elements[i] = -1;//初始化队列数组项的值为-1，表示没有存储元素
            }
        }

        /**
         * 添加新元素到队列尾部
         * */
        public void offer(int x){
            elements[tail] = x;
            tail++;
            count++;
        }

        /**
         * 队头元素出队
         * */
        public int poll(){
            if(elements[front]==-1){
                return -1;
            }
            int result = elements[front];
            front++;
            count--;
            return result;
        }

        /**
         * 查询队头元素
         * */
        public int peek(){
            return elements[front];
        }

        /**
         * 查询队列大小
         * */
        public int size(){
            return count;
        }
    }


}
