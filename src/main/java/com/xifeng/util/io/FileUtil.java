/**
 * Project Name:MyUtil <br>
 * File Name:FileUtil.java <br>
 * Package Name:com.xifeng.util.io <br>
 * @author xiezbmf
 * Date:2018年5月28日下午2:49:31 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: FileUtil <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日下午2:49:31 <br>
 * @version
 * @since JDK 1.8
 */
public class FileUtil {

	public static void writeFile(String filePath,String content) {
		File file = new File(filePath);
		File parentFile = new File(file.getParent());
		if(!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content.getBytes("UTF-8"));
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
						
				}
			}
		}
	}
	
	public static void copyFile(File file,String path) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(new File(path));
			byte[] b = new byte[4096];
			
			for(int len = 0;(len = fis.read(b)) != -1;) {
				fos.write(b,0,len);
			}
			fos.flush();
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
						
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
						
				}
			}
		}
	}
	
	public static void dealFundFile(String fileUrl,String outputFileUrl) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			fis = new FileInputStream(fileUrl);
			fos = new FileOutputStream(new File(outputFileUrl));
			byte[] b = new byte[4096];
			
			for(int len = 0;(len = fis.read(b)) != -1;) {
				bos.write(b,0,len);
			}
			
			String content = new String(bos.toByteArray());
			String[] rows = content.split("],");
			System.out.println(rows.length);
			System.out.println(rows[rows.length-1]);
			for(String row:rows) {
				String[] cloumns = row.replace("[", "").replace("]", "").split(",");
				fos.write((cloumns[0]+","+cloumns[1]+"\n").getBytes());
			}
			fos.flush();
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
						
				}
			}
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
						
				}
			}
		}
	}
	public static void main(String[] args) {
		String fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data01.htm";
		String outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data01-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
//		
//		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data02.htm";
//		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data02-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
//		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data03.htm";
//		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data03-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
//		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data04.htm";
//		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data04-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
//		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data05.htm";
//		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data05-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
//		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data06.htm";
//		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data06-simple.htm";
//		dealFundFile(fileUrl,outputFileUrl);
		
		fileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data_gp.htm";
		outputFileUrl = "G:\\third_source\\myUtil\\src\\main\\resources\\fund\\Fund_JJJZ_Data_gp-simple.htm";
		dealFundFile(fileUrl,outputFileUrl);
	}
}

	