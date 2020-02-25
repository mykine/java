package highlevel;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class EnvInfo {

	public static void main(String[] args)
	{
//		//获取所有环境变量
//		Map<String,String> env = System.getenv();
//		for (Entry<String, String> entry : env.entrySet())
//		{			
//			System.out.println(entry.getKey()+":"+entry.getValue());
//		}
//		//获取JAVA_HOME环境变量的值
//		String s = System.getenv("JAVA_HOME");
//		System.out.println(s);
		
		Properties prop = System.getProperties();
		prop.setProperty("我的密钥", "123456");
		for ( Entry<Object,Object> entry : prop.entrySet() ) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println("java_home:"+prop.getProperty("java.home"));
		System.out.println("我的密钥:"+prop.getProperty("我的密钥"));
	}
	
}
