package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * 利用Properties类操作XML配置文件
 * */
public class PropertiesXml {

	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		File input = new File("config/db.xml");
		//读取XML操作
		prop.loadFromXML(new FileInputStream(input));
		String host = prop.getProperty("host");
		String database = prop.getProperty("database");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		System.out.println("host="+host+",database="+database+",user="+user+",password="+password);
		if(user.equals("root") && password.equals("123456")) {
			System.out.println("用户名和密码验证通过");
		}else {
			System.out.println("用户名或密码错误");
		}
		//写入XML操作
		prop.setProperty("redis-ip", "127.0.0.1");
		prop.setProperty("redis-db", "0");
		prop.setProperty("redis-auth", "r123456");
		prop.storeToXML(new FileOutputStream(input), null);
		String redis_ip = prop.getProperty("redis-ip");
		String redis_db = prop.getProperty("redis-db");
		String redis_auth = prop.getProperty("redis-auth");		
		System.out.println("redis-ip="+redis_ip+",redis-db="+redis_db+",redis-auth="+redis_auth);
	}


}
