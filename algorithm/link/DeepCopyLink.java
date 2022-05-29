package algorithm.link;

import java.util.HashMap;

/**
 * 复杂链表的深度拷贝,要求拷贝后的每个节点的数值是原来的1000倍，其他关系保持不变
 * 已知一个负责链表,除了有next指针字段指向下一个节点，还有个random指针字段随机指向链表中任意节点(也可为空),求这个链表的深度拷贝
 * 深度拷贝是指：构造生成一个全新链表，节点的值以及节点之间的逻辑关系与原链表保持一致，并且即使破坏原链表，新链表也不受影响，仍然可用。
 * 关键点：节点之间的关系(无论时next的还是random的)属于逻辑关系，要拷贝就要先想办法描述这种逻辑关系才能执行关系的拷贝，而利用节点序号与节点指针的映射关系可以实现逻辑关系的描述
 * 思路：1.遍历一次原链表head1，拷贝next逻辑关系到新链表head2中，同时用hash变量hashMap1、hashMap2分别存储原链表、新链表中各自的节点与节点序号的关系，key是节点指针,value是节点序号
 *      2.再遍历一次原链表head1，依据hashMap1将原链表的random逻辑关系存储到另外一个hash变量存储hashMap3中，key是当前遍历到的节点节点序号，value是random指向的节点序号
 *      3.遍历一次新链表head2，依据hashMap2和hashMap3拷贝新链表中的random逻辑结构到新链表中
 * */
public class DeepCopyLink {

    public static void main(String[] args) {
        Node head = new Node(1,null,null);
        Node node2 = new Node(2,null,null);
        Node node3 = new Node(3,null,null);
        Node node4 = new Node(4,null,null);
        Node node5 = new Node(5,null,null);
        Node node6 = new Node(6,null,null);

        head.next = node2;
        head.random = node5;

        node2.next = node3;
        node2.random = null;

        node3.next = node4;
        node3.random = head;

        node4.next = node5;
        node4.random = node2;

        node5.next = null;
        node5.random = node4;


        System.out.println("原链表为:");
        echoHead(head);

        System.out.println("深度拷贝后的新链表为:");
        Node newHead = deepCopylink(head);
        echoHead(newHead);

    }

    /**
     * 深度拷贝成新链表
     * */
    static Node deepCopylink(Node head){
        Node newHead = null;
        //遍历原链表拷贝数值到新链表节点中，并且存储原链表各自的节点指针与序号关系到hashMap1结构中，以及存储新链表节点序号和节点指针关系到hashMap2中
        Node headOldCopy1 = head;
        HashMap<Node,Integer> hashMap1 = new HashMap<>();
        HashMap<Integer,Node> hashMap2 = new HashMap<>();

        int n = 0;
        Node tailNewLink =  null;//存储新链表尾部节点
        while (headOldCopy1!=null){
            n++;
            hashMap1.put(headOldCopy1,n);
            Node newNode = new Node(headOldCopy1.getValue()*1000,null,null);
            hashMap2.put(n,newNode);
            if(newHead==null){
                //新链表首个节点加入时
                newHead = newNode;
                tailNewLink = newHead;
            }else{
                tailNewLink.setNext(newNode);
                tailNewLink = tailNewLink.getNext();
            }
            headOldCopy1 = headOldCopy1.getNext();
        }

        //再次遍历原链表，将节点之间的random逻辑关系存储到hashMap3结构中
        HashMap<Integer,Integer> hashMap3 = new HashMap<>(); //key是节点序号，value是节点的random指向的节点序号，random为空时value也为空
        Node headOldCopy2 = head;
        n=0;//计数器归零
        while (headOldCopy2 != null) {
            n++;
            if(headOldCopy2.getRandom()!=null){
                hashMap3.put(n,hashMap1.get(headOldCopy2.getRandom()));
            }else {
                hashMap3.put(n,null);
            }
            headOldCopy2 = headOldCopy2.getNext();
        }

        //遍历新链表，将原链表节点的random逻辑关系复制到新链表中
        Node newHeadCopy = newHead;
        n=0;//计数器归零
        while (newHeadCopy != null) {
            n++;
            newHeadCopy.setRandom(hashMap2.get(hashMap3.get(n)));
            newHeadCopy = newHeadCopy.getNext();
        }
        return newHead;
    }

    static void echoHead(Node head){
        while (null!=head){
            System.out.print("节点值"+head.getValue()+"的random数值是"+(head.getRandom()!=null ? head.getRandom().getValue() : "空")+"  " );
            head=head.next;
        }
        System.out.println("");
    }

    static class  Node{
        private int value;
        private Node next;
        private Node random;

        public Node(int value,Node next,Node random){
            this.value = value;
            this.next = next;
            this.random = random;
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

        public Node getRandom() {
            return random;
        }

        public void setRandom(Node random) {
            this.random = random;
        }
    }
}
