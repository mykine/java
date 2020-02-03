package highlevel;

import java.math.BigDecimal;

//包装类:对基本类型的常用操作进行封装
public class Packaging {
	
	public static void main(String args[])
	{
		System.out.println(Integer.toBinaryString(10));
		Integer it = new Integer(100);
		System.out.println(it.getClass());
		
		Float pai = new Float("3.1415");
		System.out.println(pai.toString());
		
		float f = new Float(3.1415);
		System.out.println(f+"");
		
		Boolean b = new Boolean("true");
		Boolean b1 = new Boolean("true1");//除true外都是false
		Boolean b2 = null;
		System.out.println(b);
		System.out.println(b1.toString());
		System.out.println(b2+"");
		
		Number num = new BigDecimal("99999999999999999999999999999");
		System.out.println(num);
		
		String str = "AAA";
		if(str=="AAA") {
			System.out.println("str是AAA");
		}
		String str1 = new String("abc");
		if(str1.equals("abc")) {
			System.out.println("str1是abc");
		}
		String str2 = new String("123");
		if("123".equals(str2)) {
			System.out.println("str2是123");
		}
		
		String bStr = new String("true");	
		Boolean bb = Boolean.parseBoolean(bStr);
		System.out.println(bb);
		
		Integer in1 = Integer.valueOf(12);//valueof方法返回包装类对象,装箱：基本类型转换为包装类对象
		System.out.println(in1+"");
		int in2 = in1;//拆箱:包装类对象转换为基本类型
		System.out.println(in2);
		
	}
}
