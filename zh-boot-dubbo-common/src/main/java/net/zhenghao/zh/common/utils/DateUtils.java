package net.zhenghao.zh.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2018年1月16日 上午10:26:07
 * DateUtils.java
 */
public class DateUtils {

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间格式(yyyy年M月dd日 ah:mm:ss) 代码生成器使用 */
	public final static String DATE_TIME_CHN_PATTERN = "yyyy年M月dd日 ah:mm:ss";

	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}
	
	/**
	 * 获取时间格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateTime() {
		return format(new Date(), DATE_TIME_PATTERN);
	}
	
	/**
	 * 获取时间格式yyyy-MM-dd
	 * @return
	 */
	public static String formatDate() {
		return format(new Date(), DATE_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}
}
