package algorithm.link;

import java.util.HashMap;

/**
 * 求两链表的交点-要求时间复杂度O(n)且空间复杂度O(1)
 * 已知链表head1和链表head2相交，求该交点对应的节点
 * 算法思路：利用数学思维：交点左边的两个链表长度不一致，将较长链表多出的长度砍去，然后两个链表的长度将相等，可以同时遍历比较节点是否相等求出交点
 *     1.计算两链表长度
 *     2.将较长链表头节点往后移动多出的长度
 *     3.同时遍历两链表，比较两个节点是否一致，一致就是说明是交点直接返回结果
 *
 * */
public class CommonNodeOfTwoLink2 {

    public static void main(String[] args) {
        Node head1 = new Node(1,null);
        Node head1_2 = new Node(12,null);
        Node head1_3 = new Node(13,null);
        head1.next = head1_2;
        head1_2.next = head1_3;

        Node head2 = new Node(2,null);
        Node head2_2 = new Node(22,null);
        Node head2_3 = new Node(23,null);
        Node head2_4 = new Node(24,null);
        Node head2_5 = new Node(25,null);
        head2.next = head2_2;
        head2_2.next = head2_3;
        head2_3.next = head2_4;
        head2_4.next = head2_5;

        Node head3 = new Node(3,null);
        Node head3_2 = new Node(32,null);
        Node head3_3 = new Node(33,null);
        head3.next = head3_2;
        head3_2.next = head3_3;


        head1_3.next = head3_2;
        head2_5.next = head3_2;

        System.out.print("head1:");
        echoHead(head1);

        System.out.print("head2:");
        echoHead(head2);

        Node commonNode = commonNode(head1,head2);
        System.out.println("head1和head2的交点是:"+commonNode.getValue());
    }

    /**
     * 求两链表的交点-时间复杂度O(n)的算法
     * */
    static Node commonNode(Node head1,Node head2){
        int lenHead1 = 0;
        int lenHead2 = 0;
        //计算两个链表长度
        Node headA = head1;
        Node headB = head2;
        while (headA!=null){
            lenHead1++;
            headA = headA.next;
        }
        while (headB!=null){
            lenHead2++;
            headB = headB.next;
        }

        //移动较长链表头节点，使得两链表长度一致
        if(lenHead1>lenHead2){
            int moveN = lenHead1 - lenHead2;
            while (moveN>0){
                moveN--;
                head1=head1.next;
            }
        }else {
            int moveN = lenHead2 - lenHead1;
            while (moveN>0){
                moveN--;
                head2=head2.next;
            }
        }
        //同时遍历两个链表，比较节点是否是交点
        while (head1!=null){
            if(head1 == head2){
                return head1;
            }else{
                head1 = head1.next;
                head2 = head2.next;
            }
        }

        return null;
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
