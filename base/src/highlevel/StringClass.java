package highlevel;

import java.util.Scanner;

public class StringClass {

	private static Scanner input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 字符串相等用equal方法
		 * ==是比较左右两边所在内存是否相等
		 */
		String s1 = "dengyu";//"dengyu"是常量，等内容常量共用同一块内存
		String s2 = "dengyu";
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		
		String s3 = new String("dengyu");//new开辟新内存空间存放字符串
		System.out.println(s1==s3);
		System.out.println(s1.equals(s3));
		
		String s4 = new String("dengyu");
		System.out.println(s3==s4);
		System.out.println(s3.equals(s4));
		
//		System.exit(0);
		
		String verifyCode = "79CD";
		String name,passwd="";
		input = new Scanner(System.in);
		System.out.println("请输入用户名:");
		name = input.next();
		while(passwd.length()<6) {			
			System.out.println("请输入密码:");
			passwd = input.next();
			if( passwd.length() < 6  ) 
			{
				System.out.println("密码要大于6位，请重新输入:");
			}
		}
		
		
		System.out.println("请输入验证码:(79CD)");
		verifyCode = input.next();
		if("79CD".equalsIgnoreCase(verifyCode))
		{
			System.out.println("注册成功! 用户名:"+name+",密码:"+passwd);
		}else {
			System.out.println("注册失败!!!");
		}
		System.exit(0);
	}

}
