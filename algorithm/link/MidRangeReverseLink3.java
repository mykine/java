package algorithm.link;

/**
 * 链表中间段逆序，不能申请额外空间(即不新建数组、集合等存储型数据结构，保证空间复杂度是O(1))-反向思维版本
 * */
public class MidRangeReverseLink3 {

    public static void main(String[] args) {
        Node head = initLink(6);
        echoHead(head);
        int m=2,n=3;
        Node newHead = reverseRangeMtoNLink(head,m,n);
        System.out.println("***** 从"+m+"~"+n+"区间段逆序链表后是：");
        echoHead(newHead);
    }

    /**
     * 从链表的第m至第n个区间反转链表,且不申请额外存储空间(即不新建数组、集合等存储型数据结构，保证空间复杂度是O(1))
     * */
    static Node reverseRangeMtoNLink(Node head,int m,int n){
        int count = n-m+1;//要逆序的节点个数
        Node newHead = head;//逆序后新链表的头节点
        Node next = null;//临时存储下一节点的变量
        Node beforeRNode = null;//逆序前一个节点
        Node rhead = null;//逆序子链表头节点
        Node rTail = null;//逆序子链表尾部节点

        //将head移动到逆序开始节点
        while (head!=null && (--m)>0){
            beforeRNode = head;
            head = head.next;
        }
        rTail = head;
        //逆序操作
        while(count>0){
            next = head.next;
            head.next = rhead;
            rhead = head;
            head = next;
            count--;
        }

        //逆序完毕后拼接成完整链表
        rTail.next = next;
        if(beforeRNode==null){
            //从第1个节点开始逆序的情况
            newHead = rhead;
        }else{
            beforeRNode.next = rhead;
        }
        return newHead;
    }

    static void echoHead(Node head){
        while (null!=head){
            System.out.println(head.getValue());
            head=head.next;
        }
    }

    static Node initLink(int n){
        Node head = null;
        Node tair = null;
        for (int i = 0; i < n; i++) {
            if(0==i){
                head = new Node(1,null);
                tair = head;
            }else{
                Node newNode= new Node(i+1,null);
                tair.next = newNode;
                tair = newNode;
            }
        }
        return head;
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
