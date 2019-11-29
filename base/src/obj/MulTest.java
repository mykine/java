package obj;

public class MulTest {

	int num1=2;
	
	public int mul(int param)
	{
		int num1=10;
		return this.num1*param*num1;
	}
	
	public void mul1(int param)
	{
		System.out.println(this.mul(param) * this.num1);
	}
	
}
