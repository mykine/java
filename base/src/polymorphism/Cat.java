package polymorphism;

public class Cat extends Pet {

	public Cat(String name)
	{
		super(name);
	}
	
	@Override
	public void eat(String food) {
		// TODO Auto-generated method stub		
		System.out.println( " 喵喵~ " + super.getName() + " 吃 " + food );
	}
	
	public void miaomiao() {
		System.out.println(  super.getName() + ": 喵喵~ " );
	}

}
