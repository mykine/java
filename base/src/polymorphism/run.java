package polymorphism;

public class run {
	
	public static void main(String args[])
	{
		Man man = new Man();
		//多态实现的实现方式：父类引用执行子类对象+子类重写了父类方法
		Dog dog = new Dog("萨摩");
		man.feed(dog, "骨头");		
		Cat cat = new Cat("汤姆");
		man.feed(cat, "鱼");
		
		Pet[] pets = new Pet[2]; 
		pets[0] = new Dog("博美");
		pets[1] = new Cat("英短");
		for(int i =0 ; i<pets.length;i++)
		{
			if(pets[i] instanceof Dog) {
				Dog d = (Dog)pets[i];//父类转子类要强转
				d.wangwang();
			}else if(pets[i] instanceof Cat){
				Cat c = (Cat) pets[i];
				c.miaomiao();
			}
		}
	}
	
}
