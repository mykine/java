package obj;

public class Person {

	public String name;
	public int age;
	
	public void ShowPrice() 
	{
		System.out.print(name+"的年龄是"+age+",门票是");
		if( age > 18 ) {
			System.out.println("¥10.00");
		}else {
			System.out.println("¥5.00");
		}
	}
}
