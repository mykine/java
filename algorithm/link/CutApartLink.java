package algorithm.link;

import java.util.HashMap;

/**
 * 链表分割
 * 已知一个链表头指针head，以及一个数值x，将小于x值的节点放于大于等于x值的节点前面，并保持这些节点原来的相对位置
 * 比如：原链表:       1->4->3->2->5->2 ，x=3
 *      变换后新链表为:1->2->2->4->3->5
 * 思路：1.使用两个变量head1,head2，分别存储小于x值的子链表、大于等于x值的子链表
 *      2.将head1、head2拼接成结果链表
 * */
public class CutApartLink {

    public static void main(String[] args) {
        Node node6 = new Node(2,null);
        Node node5 = new Node(5,node6);
        Node node4 = new Node(2,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(4,node3);
        Node head = new Node(1,node2);

        System.out.println("原链表为:");
        echoHead(head);
        int x = 3;
        Node newLink = cutapartLink(head,x);

        System.out.println("分割后链表为:");
        echoHead(newLink);

    }

    /**
     * 分割链表
     * */
    static Node cutapartLink(Node head,int x){
        Node head1 = null;//小于x值的子链表头指针
        Node head2 = null;//大于等于x值的子链表头指针
        Node tmpTail1 = null;//小于x值的子链表临时尾节点
        Node tmpTail2 = null;//大于等于x值的子链表临时尾节点
        //切分为两个子链表
        while (head!=null){
            Node next= head.next;//临时变量存储下一个节点
            head.next = null;
            if (head.getValue() < x) {
                if( null == head1 ){
                    //子链表首个节点加入时
                    head1 = head;
                    tmpTail1 = head1;
                }else{
                    tmpTail1.next = head;
                    tmpTail1 = tmpTail1.next;
                }
            }else{
                if( null == head2 ){
                    //子链表首个节点加入时
                    head2 = head;
                    tmpTail2 = head2;
                }else{
                    tmpTail2.next = head;
                    tmpTail2 = tmpTail2.next;
                }
            }

            head = next;
        }


        if (null == head1) {
            //全部节点都大于等于x的情况
            return head2;
        }else if (null == head2) {
            //全部节点都小于x的情况
            return head1;
        }else {
            //合并两个子链表返回结果
            tmpTail1.next = head2;
            return head1;
        }
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
