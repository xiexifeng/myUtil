/**
 * Project Name:MyUtil <br>
 * File Name:StringUtil.java <br>
 * Package Name:com.xifeng.util <br>
 * @author xiezbmf
 * Date:2018年5月28日下午2:03:16 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util;

import java.util.UUID;

import org.springframework.util.StringUtils;
/**
 * ClassName: StringUtil <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日下午2:03:16 <br>
 * @version
 * @since JDK 1.8
 */
public class StringUtil extends StringUtils {
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

	public static int String2Int(String val, int dv) {
		try {
			if (StringUtil.isNotEmpty(val)) {
				int v = Integer.valueOf(val);
				return v;
			}
		} catch (NumberFormatException e) {
		}
		return dv;
	}

	public static long String2Long(String val, long dv) {

		try {
			if (StringUtil.isNotEmpty(val)) {
				long v = Long.valueOf(val);
				return v;
			}
		} catch (NumberFormatException e) {
		}
		return dv;
	}

	/**
	 * 进行html编码操作
	 * 
	 * @param s  转码后的字符
	 * @return
	 */
	public static String encodeHTML(String s) {
		if (isNotEmpty(s)) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				char ch = s.charAt(i);
				switch (ch) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\'':
					sb.append("&#39;");
					break;
				default:
					sb.append(ch);
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * html解码
	 * 
	 * @param s 解码后的字符
	 * @return
	 */
	public static String decodeHTML(String s) {
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&quot;", "\"");
		s = s.replaceAll("&nbsp;", " ");
		s = s.replaceAll("&amp;", "&");
		s = s.replace("&#39;", "'");
		return s;
	}

	/**
	 * 
	 * getUUID32:(获取32位uuid). <br/>
	 *
	 * @author xiezbmf 
	 * @Date:2016年9月12日下午3:12:37 <br/>
	 * @return
	 */
	public static String getUUID32() {
		UUID uuid = UUID.randomUUID();
		String s = uuid.toString();
		s = s.replace("-", "");
		return s;
	}

	

	

	public static String getPrefixPadding(int num, int length, char prefix){
		return getPrefixPadding(String.valueOf(num), length, prefix);
	}
	
	public static String getPrefixPadding(Long num, int length, char prefix){
		return getPrefixPadding(String.valueOf(num), length, prefix);
	}
	
	/**
	 * 
	 * getPrefixPadding:前缀补齐字符串，当长度超长时会截取. <br>
	 *
	 * @author xiezbmf
	 * @Date 2017年10月25日上午9:29:33 <br>
	 * @param old 原始字符串
	 * @param length 拼接后的字符串长度
	 * @param prefix 前缀补齐字符
	 * @return
	 */
	public static String getPrefixPadding(String old, int length, char prefix){
		StringBuilder sb = new StringBuilder();
		int serLen = old.length();
		if (serLen <= length) {
			int len = length - serLen;
			for (int i = 0; i < len; i++) {
				sb.append(prefix);
			}
			sb.append(old);
			return sb.toString();
		} else {
			return old.substring(0, length);
		}
	}

	/**
	 * 
	 * underscoreToCamelCase:下划线转驼峰. <br>
	 *
	 * @author xiezbmf
	 * @Date 2017年7月26日上午10:54:35 <br>
	 * @param colName
	 * @return
	 */
	public static String underscoreToCamelCase(String colName) {
		if (isEmpty(colName)) {
			return colName;
		}
		colName = colName.toLowerCase();
		String[] cols = colName.split("_");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cols.length; i++) {
			if (i == 0) {
				sb.append(cols[0]);
				continue;
			}
			char c = cols[i].charAt(0);
			char C = (char) (c - 32);
			sb.append(C).append(cols[i].substring(1));
		}
		return sb.toString();
	}

	public static boolean hasIn(String key, String[] keys) {

		for (String str : keys) {
			if (str.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public static String strArrToString(String[] arr) {
		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s).append(",");
		}
		if (sb.length() > 0) {
			return sb.deleteCharAt(sb.length() - 1).toString();
		}
		return null;
	}

	/**
	 * 
	 * firstUpperCase:将首字母大写. <br>
	 *
	 * @author xiezbmf
	 * @Date 2017年8月16日上午10:51:01 <br>
	 * @param name
	 * @return
	 */
	public static String firstUpperCase(String name) {
		char c = name.charAt(0);
		String C = new String(new char[] { c }).toUpperCase();
		return C + name.substring(1);
	}
	public static String firstLowerCase(String attr){
		char c = attr.charAt(0);
		String C = new String(new char[]{c}).toLowerCase();
		return C + attr.substring(1);
	}

	public static String listToString(String[] list, String separator) {
		if (list == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String str : list) {
			sb.append(str).append(separator);
		}
		return sb.toString();
	}
	
}

	