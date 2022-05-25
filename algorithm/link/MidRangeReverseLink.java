package algorithm.link;

/**
 * 链表中间段逆序，不能申请额外空间
 * */
public class MidRangeReverseLink {

    public static void main(String[] args) {
        Node head = initLink(6);
        echoHead(head);
        int m=1,n=5;
        Node newHead = reverseRangeMtoNLink(head,m,n);
        System.out.println("***** 从"+m+"~"+n+"区间段逆序链表后是：");
        echoHead(newHead);
    }

    /**
     * 从链表的第n至第m个区间反转链表
     * */
    static Node reverseRangeMtoNLink(Node head,int m,int n){
        int i=0;
        Node newHead = head;
        Node next = head;//临时保存被操作节点的下一个节点
        Node rHead = null;//逆序子链表的头节点
        Node beforeRNode = null;//逆序子链表头节点的前一个节点
        Node tairRNode = null;//逆序子链表的尾节点

        while(next!=null){
            i++;
            if(i<m){
                //将head移动到逆序开始处
                if(i==(m-1)){
                    beforeRNode = head;
                }
                head = head.next;
                next = head;

            }else if(i>=m && i<=n){
                //处于逆序区间
                next = head.next;
                head.next = rHead;
                rHead = head;
                if(m==i){
                    tairRNode = head;
                }
                head = next;
                if(i==n){
                    //逆序完毕就停止操作
                    break;
                }
            }
        }
        //逆序完子链表后拼接成完整的链表
        tairRNode.next = next;
        if(beforeRNode==null){
            //从第一个节点进行逆序的情况
            return rHead;
        }else {
            beforeRNode.next = rHead;
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
