package highlevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub				
		System.out.println(getNowTime1());
		System.out.println(getNowTime2());
		System.out.println(getWeek());
		
		
		// 精确到毫秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(new Date().getTime());

        // 精确到秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
        System.out.println(new Date().getTime() / 1000);

        // 精确到毫秒
        // 获取指定格式的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        // 输出字符串
        System.out.println(df.format(new Date()));
        // 获取指定时间Date对象，参数是时间戳，只能精确到秒
        System.out.println(new Date(1510369871));
        df.getCalendar();
        // 获取指定时间的时间戳
        try {
            System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").parse("2017/11/11 11:11:11:111").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	
	//获取当前时间1
	public static String getNowTime1() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
	
	//获取当前时间2
	public static String getNowTime2() {
		Calendar cd = Calendar.getInstance();
		return cd.get(Calendar.YEAR) + "-" + (cd.get(Calendar.MONTH)+1) + "-" + cd.get(Calendar.DAY_OF_MONTH) + " " 
				+ cd.get(Calendar.HOUR_OF_DAY) + ":" + cd.get(Calendar.MINUTE) + ":" + cd.get(Calendar.SECOND);
	}
	
	//今天是星期几
	public static String getWeek() {
		Calendar cd = Calendar.getInstance();
		return "今天是星期" + (cd.get(Calendar.DAY_OF_WEEK)-1) ;
	}

}
