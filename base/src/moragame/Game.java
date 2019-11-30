package moragame;

import java.util.Scanner;

/**
 * 游戏控制中心
 * */
public class Game {
	
	Person person;
	Computer computer;
	int count;
	
/***
 * 初始化游戏
 * */
	public void initGame()
	{		
		this.person = new Person();
		this.computer = new Computer();
		this.count = 0;
	}
	
	/***
	 * 赢家
	 * @param int personMora 玩家出拳
	 * @param int computerMora 机器人出拳
	 * @return int 1表示玩家赢,2表示机器人赢,0表示平手
	 * */
		public int winner(int personMora,int computerMora)
		{		
			if( (1 == personMora && 2 == computerMora) || (2 == personMora && 3 == computerMora) || (3 == personMora && 1 == computerMora) ) 
			{
				return 1;	
			}
			if( (2 == personMora && 1 == computerMora) || (3 == personMora && 2 == computerMora) || (1 == personMora && 3 == computerMora) ) 
			{
				return 2;	
			}
			
			return 0;
		}
	
	public  void startGame()
	{
		this.initGame();
		System.out.println("**********************************************");
		System.out.println("**************\t欢迎来到猜拳游戏\t**************");
		System.out.println("****** \t\t\t\t\t *****");
		System.out.println("****** \t\t请选择对手:\t\t *****");
		System.out.println("****** \t\t1.刘备\t\t\t *****");
		System.out.println("****** \t\t2.关羽\t\t\t *****");
		System.out.println("****** \t\t3.张飞\t\t\t *****");
		System.out.println("选择:");
		
		Scanner scan = new Scanner(System.in);
		int selectComputer = scan.nextInt();
		switch( selectComputer )
		{
			case 1: this.computer.name = "刘备";
				break;
			case 2: this.computer.name = "关羽";
				break;
			default: this.computer.name = "张飞";
		}
		System.out.println("输入玩家姓名:");
		this.person.name = scan.next();
		String end = "no";//是否解释，输入yes结束
		do {
			//****循环体开始******			
			//请用户输入数字出拳
			int personMora = this.person.mora();			
			//显示机器人出拳
			int computerMora = this.computer.mora();
			//显示本轮游戏结果
			int res = this.winner(personMora, computerMora);
			if( 1 == res ) {
				this.person.score++;
			}else if( 2 == res ) {
				this.computer.score++;
			}
			//选择操作
			System.out.println("是否结束(yes/no)");
			end = scan.next();			
			//****循环体结束******
		}while(!end.equals("yes"));
		System.out.print("游戏结束:"+this.person.name+"得分="+this.person.score+","+this.computer.name+"得分="+this.computer.score);
		String gameRes = "";
		if( this.person.score > this.computer.score ) {
			 gameRes = this.person.name+"赢了";	
		}else if( this.person.score < this.computer.score ) {
			 gameRes = this.computer.name+"赢了";
		}else {
			 gameRes = "平手";
		}
		System.out.print(" "+gameRes);
		//统计游戏次数和胜负结果
	}
}
