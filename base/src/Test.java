import obj.Cat;
import obj.Person;
public class Test {
	public static void main(String[] args)
	{
		Cat cat = new Cat();		
		cat.eat();
		
		Person p1 =new Person();
		p1.name = "小明爸爸";
		p1.age = 30;
		p1.ShowPrice();
		
		Person p2 =new Person();
		p2.name = "小明";
		p2.age = 3;
		p2.ShowPrice();
	}
}
