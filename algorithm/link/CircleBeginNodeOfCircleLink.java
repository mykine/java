package algorithm.link;

import java.util.HashMap;

/**
 * 求环形链表的环起点节点-不考虑空间复杂度的算法
 * 已知链表head有环，求环的起点
 * 时间复杂度O(n)的算法思路：
 * 遍历链表，用一个数组型存储结构存储遍历到的节点，并且在存储之前先判断该节点是否已经存在数组中，如果存在说明该节点就是环的起点，直接返回该节点
 * */
public class CircleBeginNodeOfCircleLink {

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
     * 求环形链表的环起点节点-时间复杂度O(n)、空间复杂度O(n)
     * */
    static Node startNodeInCircleLink(Node head){
        Node result = null;
        HashMap<Node,Integer> hashMap = new HashMap<>();
        while (null!=head){
            if(hashMap.containsKey(head)){
                return head;
            }
            hashMap.put(head, head.getValue());
            head = head.next;
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
