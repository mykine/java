package polymorphism;

public class Dog extends Pet {
	
	public Dog()
	{
		
	}
	
	public Dog(String name) 
	{		
		super(name);
	}

	//重写了父类的eat方法
	public void eat(String food)
	{
		System.out.println(super.getName()+"狗狗 吃 "+food);
	}
	
	//狗汪汪
	public void wangwang() {
		System.out.println( super.getName()+": 汪汪!!! " );
	}
	
}
