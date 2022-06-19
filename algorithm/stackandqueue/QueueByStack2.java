package algorithm.stackandqueue;

import java.util.Stack;

/**
 * 使用两个栈实现队列
 * 要求队列的入队、出队操作时间复杂度均为O(1)，并且空间复杂度为O(n)
 * 算法思路：
 *  1.两个栈一个负责入队、一个负责出队，两个栈共存储所有的元素
 *  2.入队直接用入队栈的push方法，时间复杂度为O(1)
 *  3.出队时，先判断出队栈是否为空，
 *    若不为空，直接弹栈出队栈，此时时间复杂度为O(1)
 *    若为空，将入队栈的元素依次弹栈，将元素按照队列先进先出的顺序存储补充到出队栈,此时时间复杂度为O(1~n),
 *    平均下来，时间复杂度大致上是O(n)
 *  4.因为出队栈的元素是由入队栈弹栈补充进去的，所以两个栈分化存储了所有元素，没有存储额外的数据，所以空间复杂度是O(n)
 * */
public class QueueByStack2 {

    //入队栈
    private Stack<Integer> stack1;

    //出队栈
    private Stack<Integer> stack2;

    public static void  main(String[] args){
        QueueByStack.MyQueue myQueue = new QueueByStack.MyQueue();
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
     * 入队
     * */
    public void offer(Integer node){
        stack1.push(node);
    }

    /**
     * 出队
     * */
    public Integer poll(){
       if(stack2.size()<=0){
           while (stack1.size()>0){
               stack2.push(stack1.pop());
           }
       }
       return stack2.pop();
    }



}
