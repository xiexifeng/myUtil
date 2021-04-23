/**
 * Project Name:MyUtil <br>
 * File Name:ExcelUtil.java <br>
 * Package Name:com.xifeng.util.excel <br>
 * @author xiezbmf
 * Date:2018年5月28日下午7:24:31 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import com.xifeng.util.db.MysqlConnection;
import com.xifeng.util.db.Table;
import com.xifeng.util.db.TableColumn;
import com.xifeng.util.db.TableDefine;

/**
 * ClassName: ExcelUtil <br>
 * Description: TODO
 * 
 * @author xiezbmf
 * @Date 2018年5月28日下午7:24:31 <br>
 * @version
 * @since JDK 1.8
 */
public class ExcelUtil {

	public void exportDataBase(OutputStream out, List<Table> tableList) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		
		HSSFCellStyle style = workbook.createCellStyle();
		// 背景色
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); 
		style.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex()); 
		
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
//		font.setColor(HSSFColor.RED.index);
		font.setBold(true);
		font.setFontName("宋体");
		style.setFont(font);
		// 设置边框
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);  
		
		int rowNum = 0;
		for (Table table : tableList) {
			List<TableColumn> columnList = table.getColumnList();
			HSSFRow rowHead = sheet.createRow(rowNum);
			HSSFCell cell = rowHead.createCell(0);
			cell.setCellValue("表名称");
			cell.setCellStyle(style);
			sheet.setColumnWidth(0, 10*256);
			cell = rowHead.createCell(1);
			cell.setCellValue(table.getTableName());
			cell.setCellStyle(style);
			sheet.setColumnWidth(1, 30*256);
			cell = rowHead.createCell(2);
			cell.setCellValue(table.getTableComment());
			cell.setCellStyle(style);
			sheet.setColumnWidth(2, 20*256);
			rowNum++;

			HSSFRow rowHead1 = sheet.createRow(rowNum);
			cell = rowHead1.createCell(0);
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			
			cell = rowHead1.createCell(1);
			cell.setCellValue("字段名称");
			cell.setCellStyle(style);
			cell = rowHead1.createCell(2);
			cell.setCellValue("数据类型");
			sheet.setColumnWidth(2, 20*256);
			cell.setCellStyle(style);
			cell = rowHead1.createCell(3);
			cell.setCellValue("字段描述");
			sheet.setColumnWidth(3, 40*256);
			cell.setCellStyle(style);
			cell = rowHead1.createCell(4);
			cell.setCellValue("是否为空");
			cell.setCellStyle(style);
			sheet.setColumnWidth(4, 10*256);
			cell = rowHead1.createCell(5);
			cell.setCellValue("默认值");
			cell.setCellStyle(style);
			sheet.setColumnWidth(5, 30*256);
			rowNum++;
			int i = 1;
			for (TableColumn tableColumn : columnList) {
				HSSFRow row = sheet.createRow(rowNum);
				rowNum++;
				cell = row.createCell(0);
				cell.setCellValue(i);
				cell = row.createCell(1);
				cell.setCellValue(tableColumn.getColumnName());
				cell = row.createCell(2);
				String typeName = tableColumn.getTypeName();
				String colSize = tableColumn.getColumnSize();
				String precision = tableColumn.getDecimalDigits();
				if ("DECIMAL".equals(typeName)) {
					typeName += "(" + colSize + "," + precision + ")";
				} else if (!"DATETIME".equals(typeName) && !"DATE".equals(typeName) && !"TIME".equals(typeName)
						&& !"TIMESTAMP".equals(typeName)) {
					typeName += "(" + colSize + ")";
				}
				cell.setCellValue(typeName);
				cell = row.createCell(3);
				cell.setCellValue(tableColumn.getRemark());
				cell = row.createCell(4);
				cell.setCellValue(tableColumn.getIsNullable());
				cell = row.createCell(5);
				cell.setCellValue(tableColumn.getColumnDef());
				i++;
			}
		}

		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String user = "sinaif_weibo_rw", pwd = "TJkGjWs4KHWYNdAY", ip = "192.168.1.181", dbName = "sinaif_king";
		Connection conn = MysqlConnection.getConnection(user, pwd, ip, dbName);
		List<Table> tableList = TableDefine.getTableCols(conn, dbName, null);
//		try {
//			OutputStream out = new FileOutputStream(new File("C:/mine/init/"+dbName+".xlsx"));
//			new ExcelUtil().exportDataBase(out, tableList);
//		} catch (FileNotFoundException e) {
//			
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				
//		}
	}
}
