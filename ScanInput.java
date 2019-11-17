import java.text.DecimalFormat;
import java.util.Scanner;

public class ScanInput {

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("总得分=");
		int score = input.nextInt();
		System.out.print("\n人数=");
		int men = input.nextInt();
		DecimalFormat format = new DecimalFormat("#0.##");		
		String avgScore = format.format((double)score / men);
		System.out.println("平均分="+avgScore);
		
		input.close();
	}
	
}

