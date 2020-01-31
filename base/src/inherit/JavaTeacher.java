package inherit;

//JAVA的继承是单一继承
public class JavaTeacher extends Teacher {

	public JavaTeacher(String name, String school) {
		super(name, school);
		// TODO Auto-generated constructor stub
	}
	
	public void teach() 
	{
		System.out.println(this.name+" 老师在 "+this.school+" 学校 " +"教Java课程");
	}

}
