package highlevel;

public class Exceptions {

	public static void main(String args[])
	{
		try {
			int a = 100,b=10;
			
			int c = a / b;
			
			System.out.println("c="+c);
			if(b<100) {
				throw new Exception("b太小了");
			}			
		}catch(ArithmeticException ex) {
//			ex.printStackTrace();
			System.out.println("除数不能是0");
		}catch(Exception ex) {
//			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}finally{
			System.out.println("finally");
		}	
	}
}
