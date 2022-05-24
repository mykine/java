package algorithm.link;

/**
 * 题目：已知链表头节点指针head，将链表逆序，不可申请额外空间
 * 解法：就地逆序法
 * */
public class ReverseLink {

    public static void main(String[] args) {
        Node head = null;
        int n = 5;
        head = initLink(n);
        echoLink(head);
        System.out.println("---反转链表后：---");
        Node newHead = reverseLink(head);
        echoLink(newHead);
    }

    public static Node reverseLink(Node head){
        Node newHead = null;
        Node next = null;

        while (head!=null){
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    public static Node initLink(int num){
        Node head = new Node(1,null);
        Node next = head;
        for (int i = 1; i < num; i++) {
            Node newNode = new Node(i+1,null);
            next.next = newNode;
            next = newNode;
        }
        return head;
    }

    public static void echoLink(Node head){
        while (head!=null){
            System.out.println("node-"+head.getValue());
            head = head.next;
        }
    }

    static class Node{
        private int value;
        private Node next;

        public Node(int value,Node next){
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
