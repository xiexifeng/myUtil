/**
 * Project Name:MyUtil <br>
 * File Name:FileUtil.java <br>
 * Package Name:com.xifeng.util.io <br>
 * @author xiezbmf
 * Date:2018年5月28日下午2:49:31 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.io;

import java.io.File;
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
}

	