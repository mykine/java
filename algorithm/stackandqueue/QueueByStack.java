package algorithm.stackandqueue;

/**
 * 使用栈实现队列-时间复杂度O(n)
 * 要求：设计一个对列，队列支持：新元素加入队列offer()、头部元素出队列poll()、查询头部元素peek()、判断队列是否空empty()四个基本操作,
 *      队列内部存储数据的结构为栈,栈的方法只能包括：压栈push()、弹栈pop()、查询栈顶元素top()、栈大小size()、是否空栈empty()等5个基本操作，
 *
 *   算法思路：队列是先进先出，栈是先进后出，如果能利用栈的方法倒序存储数据从而可实现队列的先进先出顺序，
 *            队列的offer()通过栈的push()实现，
 *            队列的poll()通过栈的pop()实现
 *            队列的peek()通过栈的top()实现
 *            队列的empty()通过栈的empty()实现
 *
 *
 *
 * */
public class QueueByStack {

    public static void  main(String[] args){
        MyQueue myQueue = new MyQueue();
        myQueue.offer(1);
        System.out.println("入队元素:"+1);
        myQueue.offer(2);
        System.out.println("入队元素:"+2);
        myQueue.offer(3);
        System.out.println("入队元素:"+3);
        myQueue.offer(4);
        System.out.println("入队元素:"+4);
        myQueue.offer(5);
        System.out.println("入队元素:"+5);
        myQueue.offer(6);
        System.out.println("入队元素:"+6);

        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时队头元素:"+myQueue.peek());


        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时队头元素:"+myQueue.peek());


        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时队头元素:"+myQueue.peek());


        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时队头元素:"+myQueue.peek());


        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时队头元素:"+myQueue.peek());


        System.out.println("---是否空队列---:"+myQueue.empty());
        System.out.println("队头元素:"+myQueue.peek());
        System.out.println("元素出队:"+myQueue.poll());
        System.out.println("此时是否空队列---:"+myQueue.empty());








    }

    /**
     * 通过栈实现队列
     * */
    public static class MyQueue{

        private  BaseStack dataStack;//数据栈

        public MyQueue(){
            dataStack = new BaseStack();
        }

        //进队-使用数据栈和临时栈双栈实现数据的倒序存储
        public void offer(int n){
            BaseStack tmpStack = new BaseStack();
            //先将数据栈中的元素依次弹出保存到临时栈中
            while (!dataStack.empty()){
                tmpStack.push(dataStack.pop());
            }
            //然后将新元素压栈到临时栈中，此时所有元素再临时栈中是正向存储的(top->bottom:5-4-3-2-1)
            tmpStack.push(n);

            //然后将临时栈的元素依次弹出保存到数据栈中,就完成了反向存储(top->bottom:1-2-3-4-5)
            while (!tmpStack.empty()){
                dataStack.push(tmpStack.pop());
            }
        }

        //出队
        public  int poll(){
            return dataStack.pop();
        }


        //查询队头元素

        public int peek(){
            return dataStack.top();
        }


        //队列判空
        public boolean empty(){
            return dataStack.empty();
        }

    }

    /**
     * 定义基础数据结构-栈-假设无穷容量的数组简单实现
     * 注意:topIndex是指向栈顶元素的指针，默认从起始位置开始
     * */
    static class BaseStack{
        private  int[] sArr = new int[1000];//栈数据物理存储结构-数组
        private  int topIndex = 0;//栈顶元素数组下标
        private  int count=0;//栈内元素个数
        public BaseStack(){
            //初始化数组项值,-1表示没有元素
            for (int i = 0; i < sArr.length; i++) {
                sArr[i] = -1;
            }
        }

        //压栈
        public  void push(int n){
            if(count==0){
                //空栈时
                sArr[topIndex] = n;
            }else {
                topIndex++;
                sArr[topIndex] = n;
            }
            count++;
        }

        //弹栈
        public  int pop(){
            if(count==0){
                return -1;//空栈时
            }
            int result = sArr[topIndex];
            sArr[topIndex] = -1;
            if(topIndex>0){
                topIndex--;
            }


            count--;
            return result;
        }

        //查询栈顶元素
        public  int top(){
            if(count==0){
                return -1;//空栈时
            }
            return sArr[topIndex];
        }

        //是否空栈
        public  boolean empty(){
            return count>0 ? false : true;
        }
    }


}
