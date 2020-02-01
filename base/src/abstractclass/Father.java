package abstractclass;

public abstract class Father extends Grandfather {

	public Father() {
		
	}
	
	public Father(String name, int age, int gender, String tel) {		
		super(name,age,gender,tel);
	}
	
	public void run() {
		System.out.println(super.getName()+"在跑步!");
	}
	
	public abstract void speak();
	
}
