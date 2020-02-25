package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemProperties {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//读取properties文件配置信息
		Properties prop = new Properties();
		File input = new File("config/config.properties");
		prop.load(new FileInputStream(input));
		System.out.println(prop.getProperty("ip"));
		
		//写入配置信息到properties文件
		Properties prop2 = new Properties();
		File output = new File("config/new.properties");
		prop2.setProperty("redis.host", "192.168.10.102");
		prop2.setProperty("mysql.host", "192.168.10.103");
		prop2.store(new FileOutputStream(output), "");
		System.out.println("写入成功");
	}

}
