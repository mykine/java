package inherit;

public class Run {

	public static void main(String args[]) 
	{
		PhpTeacher phpt = new PhpTeacher("jack","深圳");
		phpt.teach();
		JavaTeacher javaT = new JavaTeacher("james","上海");
		javaT.name = "詹姆斯";
		javaT.teach();
	}
	
}
