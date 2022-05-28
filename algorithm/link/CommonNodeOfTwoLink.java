package algorithm.link;

import java.util.HashMap;

/**
 * 求两链表的交点
 * 已知链表head1和链表head2相交，求该交点对应的节点
 * 时间复杂度O(n)的算法思路：先将一个链表的节点指针存储到数组中，然后再遍历另一个链表，检查节点是否存在数组中，第一个存在数组中的节点就是交点
 * */
public class CommonNodeOfTwoLink {

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


        head1_3.next = head3;
        head2_5.next = head3;

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
        Node result = null;

        HashMap<Node,Integer> hashMap = new HashMap<>();
        while (head1!=null){
            hashMap.put(head1, head1.getValue());
            head1 = head1.next;
        }

        while (head2!=null){
            if(hashMap.containsKey(head2)){
                return head2;
            }
            head2 = head2.next;
        }

        return result;
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
