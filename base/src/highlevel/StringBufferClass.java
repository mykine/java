package highlevel;

public class StringBufferClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//StringBuffer直接对原字符串操作
		StringBuffer strB = new StringBuffer("hello java!");
		strB.insert(6, " word ");
		System.out.println(strB);
		
		//字符串转到字符数组
		char[] strArr = strB.toString().toCharArray();
		for(int n=0;n<strArr.length;n++) {
			System.out.println(strArr[n]);
		}
		
		//字符数组转到字符串
		String str = new String(strArr);
		System.out.println(str);
	}

}
