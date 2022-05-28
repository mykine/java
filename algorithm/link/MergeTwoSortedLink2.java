package algorithm.link;

/**
 * 优化代码-已知两个已排好序的链表头节点指针head1、head2，将这两个链表合并，合并后仍有序，返回合并后的头节点
 * 要求：空间复杂度是O(1)、且时间复杂度是O(n)
 * */
public class MergeTwoSortedLink2 {

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
//        Node mergeLink = mergeTwoSortedLink(head1,head2);
        Node mergeLink = mergeTwoSortedLink(head2,head1);
        echoLink(mergeLink);
    }

    /**
     * 算法思路： 1.遍历两个已排好序的列表，比较他们的节点，小的那个节点被加入到新链表，并且将该节点所在链表头节点后移一位
     *          2.重复1操作，直到两个链表中出现至少一个链表的全部元素都加入到新链表中时结束
     *          3.将还有节点未操作的链表的剩余节点都加入到新链表中
     *          4.返回新链表头节点
     *
     * */
    static Node mergeTwoSortedLink(Node head1 ,Node head2){
        Node newLink = null;//合并后的链表头节点
        Node tailTmp=null;//存储合并链表临时尾部节点的变量
        while (head1!=null && head2!=null){
            if( head1.getValue() <= head2.getValue() ){
                if(newLink==null){
                    //首次比较的情况，确定新链表的头节点
                    newLink = head1;
                    tailTmp = head1;
                    head1 = head1.next;
                }else{
                   tailTmp.next = head1;
                   tailTmp = tailTmp.next;
                   head1 = head1.next;
                }
            }else{
                if(newLink==null){
                    //首次比较的情况，确定新链表的头节点
                    newLink = head2;
                    tailTmp = head2;
                    head2 = head2.next;
                }else{
                    tailTmp.next = head2;
                    tailTmp = tailTmp.next;
                    head2 = head2.next;
                }
            }
        }

        if(head1!=null){
            //如果head1有剩余
            tailTmp.next = head1;
        }else{
            //如果head2有剩余
            tailTmp.next = head2;
        }

        return newLink;
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
