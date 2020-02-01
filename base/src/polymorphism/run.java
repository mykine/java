package polymorphism;

public class run {
	
	public static void main(String args[])
	{
		Man man = new Man();
		//多态实现man的feed动作
		Dog dog = new Dog("萨摩");
		man.feed(dog, "骨头");		
		Cat cat = new Cat("汤姆");
		man.feed(cat, "鱼");
	}
	
}
