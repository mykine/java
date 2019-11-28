
public class MatrixArr2 {
	
	public static void main(String[] args)
	{
		int[][] arr2 = 
		{
			{1,2,3,4,5},
			{6,7,8,9,10},
			{11,12,13,14,15},
			{16,17,18,19,20},
			{21,22,23,24,25},
		};
		
		System.out.println("************* 矩阵原始输出 *************");
		for(int x=0;x<arr2.length;x++)
		{
			for(int y=0;y<arr2[x].length;y++) {
				System.out.print(arr2[x][y]+"\t");
			}
			System.out.println();
		}
		System.out.println("************* 矩阵对角线上方置0输出 *************");
		for(int x=0;x<arr2.length;x++)
		{
			for(int y=0;y<arr2[x].length;y++) {
				int showVal = x<y ? 0 : arr2[x][y]; 
				System.out.print(showVal+"\t");
			}
			System.out.println();
		}
	}
}
