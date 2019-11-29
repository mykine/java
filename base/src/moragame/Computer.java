package moragame;

/***
 * 游戏机器人
 * */
public class Computer {
	public String name;
	public int score;
	
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
		int res = (int)(Math.random()*3)+1;
		System.out.println(this.name+"出拳 "+this.resStr(res));
		return res;
	}
}
