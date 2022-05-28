package algorithm.link;

import java.util.HashMap;

/**
 * 求环形链表的环起点节点-要求空间复杂度O(1)-快慢指针赛跑算法
 * 已知链表head有环，求环的起点
 * 算法思路：数学思维的方法,，找到数学上的规律,
 * 1.利用快慢指针赛跑算法，快指针fast和慢指针slow同时从链表头部开始移动，fast指针每次移动两个节点的位置，slow指针每次移动一个节点的位置，
 *   如果链表有环，fast指针会在环内超过slow并与之相遇，这类似400米跑到内赛跑，快的人会套圈与慢的人相遇
 * 2.设链表的头节点为head,环形起点为startCircle,快慢指针首次相遇点为meet,
 *   head到startCircle的距离为a，startCircle到meet的距离为b，meet到startCicle的距离为c，
 *   则第一次相遇时，fast的路程=a+b+c+b，slow的路程=a+b
 *   而已经fast的速度时slow的2倍，跑的时间相同，所以fast路程也是slow路程的2倍,
 *   即a+b+c+b = 2(a+b)，可以得出a=c,即链表起始点到环起点的距离与相遇店到环起点的距离相等
 * 3.从上可以得出，从head和meet同时同速度前进，将相遇于startCircle，可以利用这个规律求出startCircle的位置
 *
 * 遍历链表，用一个数组型存储结构存储遍历到的节点，并且在存储之前先判断该节点是否已经存在数组中，如果存在说明该节点就是环的起点，直接返回该节点
 * */
public class CircleBeginNodeOfCircleLink2 {

    public static void main(String[] args) {
        Node node7 = new Node(7,null);
        Node node6 = new Node(6,node7);
        Node node5 = new Node(5,node6);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(2,node3);
        Node head = new Node(1,node2);
        node7.setNext(node3);

        Node result = startNodeInCircleLink(head);
        System.out.println("环形起点是:"+result.getValue());
    }

    /**
     * 求环形链表的环起点节点-快慢指针赛跑算法-时间复杂度O(n)、空间复杂度O(1)
     * */
    static Node startNodeInCircleLink(Node head){
        Node fast = head;//快指针，每次移动2个节点位置
        Node slow = head;//慢指针，每次移动1个节点位置
        Node startCircle = null;//环起点节点
        Node meet = null;//快慢指针首次相遇的节点位置

        //先求出meet
        fast=fast.next.next;
        slow = slow.next;
        while (true){
            if(fast == slow){
                meet = fast;
                break;
            }
            fast=fast.next.next;
            slow = slow.next;
        }
        //再求出startCircle
        while (head!=null){
            if(head == meet){
                startCircle = head;
                break;
            }
            head = head.next;
            meet = meet.next;
        }
        return startCircle;
    }

    static void echoHead(Node head){
        while (null!=head){
            System.out.print(head.getValue()+" ");
            head=head.next;
        }
        System.out.println("");
    }

    static class  Node{
        private int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
