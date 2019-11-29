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
		System.out.println("**********************************************");
		
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
		String end = "no";//是否解释，输入yes结束
		do {
			//****循环体开始******
			//请用户输入数字出拳
			//显示机器人出拳
			//显示本轮游戏结果
			//****循环体结束******
		}while(end!="yes");
				
		//统计游戏次数和胜负结果
	}
}
