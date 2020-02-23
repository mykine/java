package highlevel;

import java.util.LinkedList;

import polymorphism.Cat;

/***
 * 链表集合类,内存碎片空间利用率高,适合添加删除频繁的操作情景
 * */
public class CollectionListLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList();
		Cat c1 = new Cat("喵喵1");
		Cat c2 = new Cat("喵喵2");
		Cat c3 = new Cat("喵喵3");
		Cat c4 = new Cat("喵喵4");
		Cat c5 = new Cat("喵喵5");
		list.add(c1);
		list.add(c2);
		list.add(c3);
		
		Cat catFirst = (Cat) list.getFirst();
		System.out.println("第一只喵喵是:"+catFirst.getName());
		Cat catLast = (Cat) list.getLast();
		System.out.println("最后只喵喵是:"+catLast.getName());
		
		list.addFirst(c4);
		list.addLast(c5);
		
		Cat catFirst2 = (Cat) list.getFirst();
		System.out.println("第一只喵喵是:"+catFirst2.getName());
		Cat catLast2 = (Cat) list.getLast();
		System.out.println("最后只喵喵是:"+catLast2.getName());
		
		Cat catDel1 = (Cat) list.removeFirst();
		System.out.println("被删除的首只喵喵是:"+catDel1.getName());
		
		Cat catFirst3 = (Cat) list.getFirst();
		System.out.println("第一只喵喵是:"+catFirst3.getName());
		
	}

}
