package polymorphism;

/**
 * 宠物类
 * */
public abstract class Pet {

	private String name ;	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public Pet() 
	{		
	}
	
	public Pet(String name) 
	{		
		this.name = name;		
	}
	
	public abstract void eat(String food);
	
	
}
