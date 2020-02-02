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
		System.out.println(f);
		
		Boolean b = new Boolean("true");
		Boolean b1 = new Boolean("true1");//除true外都是false
		System.out.println(b);
		System.out.println(b1);
		
		Number num = new BigDecimal("99999999999999999999999999999");
		System.out.println(num);
	}
}
