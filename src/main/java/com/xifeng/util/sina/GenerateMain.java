package com.xifeng.util.sina;

import com.xifeng.util.sina.base.FileDescription;
import com.xifeng.util.sina.base.Project;
import com.xifeng.util.sina.gen.GenerateUtil;

/**
 * @Description : 
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月13日 上午10:22:36
 */
public class GenerateMain {
	public static void createProject() {
		String name = "king-finance-submodule-xxdk";
		String path = "C:/mine/source/king/finance-submodule-merchant/";
		String productEnum = "XXDK";
		String desc = "向心贷资方实现";
		Project pro = new Project(name, path, productEnum, desc);
		
		String description = "";
		String copyright = "Sinaif Software Co.,Ltd. All Rights Reserved";
		String company = "海南新浪爱问普惠科技有限公司";
		String author = "schelling";
		String version = "1.0.0";
		FileDescription fileDesc = new FileDescription(description, copyright, company, author, version);
		
		String excelPath = "C:/Users/dell/Desktop/merchant_interface.xlsx";
		excelPath = "C:/mine/newFinance/xxdk/向心贷接口-1.xlsx";
		
		String successCode = "0000";
//		pro.clear();
//		pro.init();
//		GenerateUtil.gernerateBase(pro,fileDesc);
		GenerateUtil.gernerateFromExcel(pro, fileDesc, excelPath, successCode);
//		GenerateUtil.gernerateEnumFromExcel(pro, fileDesc, excelPath);
	}
	public static void main(String[] args) {
		createProject();
		System.out.println("生成完毕");
	}
}
