import java.util.Scanner;

public class SysinArr {

	public static void main(String[] args)
	{
		int length = 5;
		int[] scores = new int[length];
		Scanner scan = new Scanner(System.in);
		for(int i=0;i<length;i++) {
			System.out.println("请输入第"+(i+1)+"个元素:");
			scores[i] = scan.nextInt();
		}
		scan.close();
		arr01.printArr(scores);
	}
}
