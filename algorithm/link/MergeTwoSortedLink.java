package algorithm.link;

/**
 * 已知两个已排好序的链表头节点指针head1、head2，将这两个链表合并，合并后仍有序，返回合并后的头节点
 * 要求：空间复杂度是O(1)、且时间复杂度是O(n)
 * */
public class MergeTwoSortedLink {
    public static void main(String[] args){
        Node head1 = new Node(1,null);
        Node node1_3 = new Node(3,null);
        Node node1_5 = new Node(5,null);
        Node node1_7 = new Node(7,null);
        Node node1_9 = new Node(9,null);
        head1.next = node1_3;
        node1_3.next = node1_5;
        node1_5.next = node1_7;
        node1_7.next = node1_9;
        System.out.print("head1:");
        echoLink(head1);

        Node head2 = new Node(2,null);
        Node node2_4 = new Node(4,null);
        Node node2_6 = new Node(6,null);
        Node node2_8 = new Node(8,null);
        Node node2_10 = new Node(10,null);
        Node node2_12 = new Node(12,null);
        head2.next = node2_4;
        node2_4.next = node2_6;
        node2_6.next = node2_8;
        node2_8.next = node2_10;
        node2_10.next = node2_12;
        System.out.print("head2:");
        echoLink(head2);

        System.out.print("after merged : ");
        Node mergeLink = mergeTwoSortedLink(head1,head2);
        echoLink(mergeLink);
    }

    static Node mergeTwoSortedLink(Node head1,Node head2){
        Node result = null;
        Node tail = null;
        while(head1!=null && head2!=null){
            if(head1.getValue() <= head2.value){
                if(result==null){
                    result = head1;
                    tail = head1;
                    head1 = head1.next;
                }else{
                    tail.next = head1;
                    tail = tail.next;
                    head1=head1.next;
                }
            }else {
                if(result==null){
                    result = head2;
                    tail = head2;
                    head2 = head2.next;
                }else{
                    tail.next = head2;
                    tail = tail.next;
                    head2=head2.next;
                }
            }
        }
        if(head1!=null){
//            while (head1!=null){
//                tail.next = head1;
//                tail = tail.next;
//                head1 = head1.next;
//            }
            tail.next = head1;
        }
        if(head2!=null){
//            while (head2!=null){
//                tail.next = head2;
//                tail = tail.next;
//                head2 = head2.next;
//            }
            tail.next = head2;
        }
        return result;
    }

    static void echoLink(Node head){
        while (head!=null){
            System.out.print(head.getValue()+" ");
            head=head.next;
        }
        System.out.println("");
    }

    static class Node{
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
