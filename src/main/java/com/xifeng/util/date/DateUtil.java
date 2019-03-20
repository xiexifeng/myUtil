/**
 * Project Name:MyUtil <br>
 * File Name:DateUtil.java <br>
 * Package Name:com.xifeng.util.date <br>
 * @author xiezbmf
 * Date:2018年5月28日下午2:02:31 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.xifeng.util.StringUtil;

/**
 * ClassName: DateUtil <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日下午2:02:31 <br>
 * @version
 * @since JDK 1.8
 */
public class DateUtil {
	public final static String DEFAULT_PATTERN = "yyyy-MM-dd";
	public final static String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
	public final static String YMD_HM = "yyyy-MM-dd HH:mm";
	public final static String YM = "yyyy-MM";
	public final static String YMD_HMS_NUM = "yyyyMMddHHmmss";
	public final static String YMD_HM_NUM = "yyyyMMddHHmm";
	public final static String YMD_NUM = "yyyyMMdd";
	public final static String YMD_H_NUM = "yyyyMMddHH";
	public final static String YYYYMM = "yyyyMM";
	public final static String YYYY = "yyyy";
	public final static String YYYYMMDD = "yyyyMMdd";
	public final static String YYYYMMDD_SEPARATOR = "yyyy/MM/dd";
	public final static String HHMMSS = "hhmmss";
	
	/**
	 * 
	 * format:默认format. <br>
	 *
	 * @author xiezbmf
	 * @Date 2017年10月18日上午9:23:29 <br>
	 * @param date
	 * @return date为空时返回空字符串
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, DEFAULT_PATTERN);
	}

	
	/**
	 * format:按pattern进行format. <br>
	 *
	 * @author xiezbmf
	 * @Date 2017年10月18日上午9:24:04 <br>
	 * @param date
	 * @param pattern
	 * @return date为空时返回空字符串
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}

	public static Date parse(String strDate) throws ParseException {
		return StringUtil.isEmpty(strDate) ? null : parse(strDate, DEFAULT_PATTERN);
	}

	public static Date parse(String strDate, String pattern)  {
		try {
			return StringUtil.isEmpty(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (Exception e) {
			return null;
		}
		
	}
}

	