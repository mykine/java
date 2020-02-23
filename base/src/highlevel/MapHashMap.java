package highlevel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import polymorphism.Cat;

/**
 * 键值对数据结构Map接口
 * */
public class MapHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String,Cat> cats = new HashMap<String,Cat>();
		Cat c1 = new Cat("猫1");
		cats.put("cat1", c1);
		Cat c2 = new Cat("猫2");
		cats.put("cat2", c2);
		Cat c3 = new Cat("猫3");
		cats.put("cat2", c2);
		
		Iterator<String> it = cats.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next()+"";
			Cat cat = cats.get(key);
			System.out.println(key+":"+cat.getName());
		}
		
		for( Map.Entry<String, Cat> entry : cats.entrySet() )
		{
			String key = entry.getKey();
			Cat cat = entry.getValue();
			System.out.println(key+":"+cat.getName());
		}
	}

}
