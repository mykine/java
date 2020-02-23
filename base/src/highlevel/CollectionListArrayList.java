package highlevel;

import java.util.ArrayList;
import java.util.Iterator;

import polymorphism.Cat;
/**
 * ArrayList是线性表：动态扩充连续存储空间的数组，每次内存大小的操作都是整块连续的空间，适合查询情景
 * */
public class CollectionListArrayList {
	
	public static void main(String args[])
	{
		ArrayList<Cat> arr = new ArrayList<Cat>();
		Cat catA = new Cat("小黑");
		arr.add(catA);
		Cat catB = new Cat("小白");
		arr.add(catB);
		Cat catC = new Cat("小C");
		arr.add(catC);
		printArr1(arr);
		printArr2(arr);
		printArr3(arr);
		System.out.println("");
		System.out.println(arr.indexOf(catB));
		arr.remove(0);
		System.out.println(arr.indexOf(catB));
		System.out.println( "1小猫:" + ((Cat) arr.get(1)).getName() );
	}
	
	public static void printArr1(ArrayList<Cat> arr)
	{
		for(int i=0;i<arr.size();i++ ) {
			Cat cat = arr.get(i);
			cat.setName(cat.getName()+"1");
			System.out.println("一只小喵:"+cat.getName());
		}
	}
	
	public static void printArr2(ArrayList<Cat> arr)
	{
		for(Object obj : arr)
		{
			Cat cat = (Cat)obj;
			System.out.print(";一只小喵:"+cat.getName());
		}
	}
	
	public static void printArr3(ArrayList<Cat> arr)
	{
		Iterator<Cat> it = arr.iterator();
		while(it.hasNext())
		{
			Cat cat = it.next();
			System.out.print(";喵喵:"+cat.getName());
		}
	}
}
