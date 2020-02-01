package abstractclass;

public abstract class Grandfather {
	
	private String name;
	private int age;
	private int gender;
	private String tel;
	
	public Grandfather() {		
		
	}
	
	
	public Grandfather(String name, int age, int gender, String tel) {		
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public abstract void run();
	
	public abstract void speak();

}
