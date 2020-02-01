package abstractclass;

public class Son extends Father {
	
	public Son() {
		
	}
	
	public Son(String name, int age, int gender, String tel) {		
		super(name,age,gender,tel);
	}

	@Override
	public void speak() {
		System.out.println(super.getName()+"说他的手机号是"+super.getTel());		
	}
	
	
}
