
public class arr01 {
	public static void main(String args[])
	{
		/******************** 数组基本用法begin ********************/
		//1.声明变量
		int[] score;
		//2.分配空间
		score = new int[5];
		//3.赋值
		for(int i=0;i<score.length;i++) 
		{
			score[i] = i+1;
		}
		//4.使用
		printArr(score);
		/******************** 数组基本用法end ********************/
		
		
		/******************** 数组用法2 ********************/
		int[] score2 = new int[]{1,2,3,4,5};
		printArr(score2);
		
		/******************** 数组用法3 ********************/
		int[] score3 = {1,2,3,4,5};
		printArr(score3);
	}
	
	public static void printArr(int[] arr) {
		System.out.println("打印数组:");
		for(int i=0;i<arr.length;i++)
		{
			System.out.println("score["+i+"]="+arr[i]);
		}
	}
	
	
}
