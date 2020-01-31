package inherit;

public class PhpTeacher extends Teacher {

	 public PhpTeacher(String name,String school){
		 super(name,school);
	 }
	 
	 public void teach() 
	 {
		 System.out.println(this.name+" 老师在 "+this.school+" 学校 " +"教Php课程");
	 }
	
}
