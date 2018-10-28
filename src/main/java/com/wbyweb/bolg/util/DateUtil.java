package com.wbyweb.bolg.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * @author WeiBiaoYi
 *
 */
public class DateUtil {
	//joda-time
	public static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYY_MM_DD="yyyy-MM-dd";
	/**
	 * @author WeiBiaoYi
	 * 字符串转换为Date
	 * @param dateTimeStr 
	 * @param formatStr 字符串格式
	 * @return
	 */
	public static Date strToDate(String dateTimeStr,String formatStr){
		DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(formatStr);
		DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
		return dateTime.toDate();
	}
	/**
	 * @author WeiBiaoYi
	 * Date转换为字符串
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToStr(Date date,String formatStr){
		if(date==null){
			return StringUtils.EMPTY;
		}
		DateTime dateTime=new DateTime(date);
		return dateTime.toString(formatStr);
	}
	/**
	 * @author WeiBiaoYi
	 * 字符串转换为Date
	 * @param dateTimeStr 
	 * @param formatStr 字符串格式
	 * @return
	 */
	public static Date strToDate(String dateTimeStr){
		DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern(FORMAT_YYY_MM_DD);
		DateTime dateTime=dateTimeFormatter.parseDateTime(dateTimeStr);
		return dateTime.toDate();
	}
	/**
	 * @author WeiBiaoYi
	 * Date转换为字符串
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToStr(Date date){
		if(date==null){
			return StringUtils.EMPTY;
		}
		DateTime dateTime=new DateTime(date);
		return dateTime.toString(STANDARD_FORMAT);
	}
}
