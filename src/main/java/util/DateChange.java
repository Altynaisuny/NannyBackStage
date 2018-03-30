package main.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换
 * @author altynai
 *
 */
public class DateChange {

    /**
     * 获取当前日期年月
     * @return
     */
    public static String getYMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 获取当前日期年月日
     * @return
     */
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 获取当前日期年月日时分秒
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }

    /**
     * 获取特定日期的时间戳
     * @return
     * @throws ParseException
     */
    public static long getDateStamp(String date) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        long ctime = d.getTime();
        return ctime;
    }

    /**
     * 获取特定时间的时间戳
     * @return
     * @throws ParseException
     */
    public static long getTimeStamp(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        long ctime = date.getTime();
        return ctime;
    }

    /**
     * 根据时间字符串获取时间区间的数组
     * @param time
     * @return
     */
    public static String[] dateSection(String time){
        if(time == null || "".equals(time)){
            return null;
        } else {
            String start = time.split(" - ")[0];
            String end = time.split(" - ")[1];
            String[] result = new String[2];
            result[0] = start + " 00:00:00";
            result[1] = end + " 23:59:59";
            return result;
        }
    }

    public static void main(String[] args) throws ParseException {
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String t = sdf.format(new Date());
		System.out.println(t);
		*/

/*		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		System.out.println(date);*/
//		System.out.println(getDateStamp("2017-08-10"));
//		System.out.println(getYMonth());

    }

}
