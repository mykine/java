
public class arr2 {

	public static void main(String args[])
	{
		//声明
		int[][] ss = new int[3][3];//或 new int[3][]
		//赋值
		for(int x=0;x<3;x++)
		{
			for(int y=0;y<3;y++) 
			{
				ss[x][y]=x*y;
			}
		}
		//使用
		for(int x=0;x<3;x++)
		{
			for(int y=0;y<3;y++) 
			{
				System.out.println("arr["+x+"]["+y+"]="+ss[x][y]);
			}
		}
		
		System.out.println("ss2打印:");
		
		/********************** 定义并赋值时[][]不要定义固定的行数列数 ***********************/
		//写法2
		int[][] ss2 = new int[][] {{1,2,3},{1,2},{1}};
		for(int x=0;x<ss2.length;x++)
		{
			for(int y=0;y<ss2[x].length;y++) {
				System.out.println("arr["+x+"]["+y+"]="+ss2[x][y]);
			}
		}
		
		//写法3
		System.out.println("ss3打印:");
		int[][] ss3 = {{1,2,3},{1,2},{1}};
		for(int x=0;x<ss3.length;x++)
		{
			for(int y=0;y<ss3[x].length;y++) {
				System.out.println("arr["+x+"]["+y+"]="+ss3[x][y]);
			}
		}
	}
	
}
