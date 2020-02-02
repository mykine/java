package polymorphism;

import enums.Gender;

/**
 * 饲养员
 * */
public class Man {
	
	private Gender gender;
	private String name;
	
	
	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Man(Gender gender, String name) {		
		this.gender = gender;
		this.name = name;
	}
	
	public Man() 
	{
		
	}

	public void feed(Pet pet,String food) 
	{
		pet.eat(food);
	}
	
	public void info()
	{
		String genderShow = this.gender == Gender.Female ? "女" : "男";
		System.out.println(this.name+"是个"+genderShow+"饲养员");
	}
	
}
