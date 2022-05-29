package algorithm.stackandqueue;

/**
 * 使用队列实现栈-核心算法：双队列操作可以实现元素的倒序存储
 * 设计一个栈，支持如下操作，栈的内部存储数据的结构为队列，队列的方法只能包括以下标准方法：从尾部加入新元素offer()、查询头部元素peek()、获取头部元素并出队poll()、获取队列大小size()
 * 要实现队列的标准方法：新元素压栈push()、弹栈pop()、获取栈顶元素top()、判断栈空empty()
 * 思路：
 *     1.利用队列的poll实现栈的pop
 *     2.利用队列的peek实现栈的top
 *     3.利用队列的size实现栈的empty
 *     4.利用队列的offer实现栈的push，但是必须保障push后的数据队列中元素顺序与一般队列的元素顺序是逆序的，才可以满足前面的1和2项操作
 * 难点：如何用队列实现栈元素的先进后出式存储
 * 考察点：push时，利用临时队列、数据队列的双队列操作实现元素调个，倒序保存到数据队列中
 *
 * */
public class StackByQueue {

    public static void  main(String[] args){

        MyStack myStack = new MyStack();
        System.out.println("压栈:1");
        myStack.push(1);
        System.out.println("压栈:2");
        myStack.push(2);
        System.out.println("压栈:3");
        myStack.push(3);
        System.out.println("压栈:4");
        myStack.push(4);
        System.out.println("压栈:5");
        myStack.push(5);
        System.out.println("压栈:6");
        myStack.push(6);

        System.out.println("栈是否为空:"+myStack.empty());
        System.out.println("栈顶元素是:"+myStack.top());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("弹栈:"+myStack.pop());
        System.out.println("栈是否为空:"+myStack.empty());
    }


    /**
     * 自定义队列基础数据结构-数组实现
     * */
    static class Queue{

        private int count = 0;//队列元素个数
        private int front = 0;//队列头部数组下标
        private int tail = 0;//队列尾部数组下标
        private int[] arr = new int[100];

        public Queue(){
            for (int i = 0; i < arr.length; i++) {
                arr[i] = -1;//初始化队列数组项的值为-1，表示没有存储元素
            }
        }

        /**
         * 添加新元素到队列尾部
         * */
        public void offer(int x){
            arr[tail] = x;
            tail++;
            count++;
        }

        /**
         * 队头元素出队
         * */
        public int poll(){
            if(arr[front]==-1){
                return -1;
            }
            int result = arr[front];
            front++;
            count--;
            return result;
        }

        /**
         * 查询队头元素
         * */
        public int peek(){
            return arr[front];
        }

        /**
         * 查询队列大小
         * */
        public int size(){
            return count;
        }
    }


    /**
     * 自定义栈-内部通过队列实现
     * */
    static class  MyStack{

        private  Queue queue;//数据队列

        public MyStack(){
            queue = new Queue();
        }

        /**
         * 压栈，利用双队列实现元素的逆序存储
         * */
        public void push(int x){
//            queue.offer(x);
            //先将新元素存入到临时队列
            Queue tmpQueue = new Queue();
            tmpQueue.offer(x);
            //然后将数据队列的值倒入到临时队列中
            int sizeQueue = queue.size();
            for (int i = 0; i < sizeQueue; i++) {
                int element = queue.poll();
                tmpQueue.offer(element);
            }
            //将临时队列的所有元素再导入数据队列中，就形成了倒序存储
            int sizeTmpQueue = tmpQueue.size();
            for (int i = 0; i < sizeTmpQueue; i++) {
                int element = tmpQueue.poll();
                queue.offer(element);
            }

        }

        /**
         * 弹栈，后进先出的效果
         * */
        public Integer pop(){
            return queue.poll();
        }

        /**
         * 获取栈顶元素
         * */
        public Integer top(){
            return queue.peek();
        }

        /**
         * 是否是空栈
         * */
        public boolean empty(){
            return queue.size()>0 ? false : true;
        }
    }
}
