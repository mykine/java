package moragame;

import java.util.Scanner;

/**
 * 游戏玩家
 * */
public class Person {
	public String name;
	public int score=0;
	
	//出拳名字
	public String resStr(int res)
	{
		String str = "";
		switch(res)
		{
		   case 1:str = "石头";
			   break; 
		   case 2:str = "剪子";
		   	   break;
		   default:str = "布";
		}
		return str;
	}
	//出拳
	public int mora() {
		System.out.println("玩家出拳:");
		System.out.println("\t 1.石头");
		System.out.println("\t 2.剪子");
		System.out.println("\t 3.布 ");
		System.out.println("请输入:");
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt();
		System.out.println(this.name+"出 "+this.resStr(res));
		return res;
	}
}
