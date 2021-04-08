package com.xifeng.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.fastjson.JSONObject;

public class MatchLogExp {
	
	public void export(OutputStream out,List<String> dataList) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		
		HSSFCellStyle style = workbook.createCellStyle();
		// 背景色
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
		style.setFillBackgroundColor(HSSFColor.YELLOW.index); 
		
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
//					font.setColor(HSSFColor.RED.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		style.setFont(font);
		// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
		int rowNum = 0;
		
		//创建表头
		HSSFRow rowHead = sheet.createRow(rowNum);
		HSSFCell cell = rowHead.createCell(0);
		cell.setCellValue("Vessel type");
		cell.setCellStyle(style);
		sheet.setColumnWidth(0, 30*256);
		cell = rowHead.createCell(1);
		cell.setCellValue("Chartering type");
		cell.setCellStyle(style);
		sheet.setColumnWidth(1, 30*256);
		cell = rowHead.createCell(2);
		cell.setCellValue("Cargo name");
		cell.setCellStyle(style);
		sheet.setColumnWidth(2, 30*256);

		cell = rowHead.createCell(3);
		cell.setCellValue("Duration");
		sheet.setColumnWidth(3, 10*256);
		cell.setCellStyle(style);
		cell = rowHead.createCell(4);
		cell.setCellValue("Ship type");
		cell.setCellStyle(style);
		sheet.setColumnWidth(4, 30*256);
		cell = rowHead.createCell(5);
		cell.setCellValue("Loading port");
		cell.setCellStyle(style);
		sheet.setColumnWidth(5, 30*256);
		
		cell = rowHead.createCell(6);
		cell.setCellValue("Discharging port");
		cell.setCellStyle(style);
		sheet.setColumnWidth(6, 30*256);
		
		cell = rowHead.createCell(7);
		cell.setCellValue("Laycan from(DMY)");
		cell.setCellStyle(style);
		sheet.setColumnWidth(7, 30*256);
		
		cell = rowHead.createCell(8);
		cell.setCellValue("Laycan to(DMY)");
		cell.setCellStyle(style);
		sheet.setColumnWidth(8, 30*256);
		
		cell = rowHead.createCell(9);
		cell.setCellValue("Volume/DWT/Vessel TEU");
		cell.setCellStyle(style);
		sheet.setColumnWidth(9, 30*256);
		
		cell = rowHead.createCell(10);
		cell.setCellValue("Flag");
		cell.setCellStyle(style);
		sheet.setColumnWidth(10, 30*256);
		
		cell = rowHead.createCell(11);
		cell.setCellValue("Class");
		cell.setCellStyle(style);
		sheet.setColumnWidth(11, 30*256);
		
		cell = rowHead.createCell(12);
		cell.setCellValue("Remark");
		cell.setCellStyle(style);
		sheet.setColumnWidth(12, 30*256);
		cell = rowHead.createCell(13);
		cell.setCellValue("Create Time");
		cell.setCellStyle(style);
		sheet.setColumnWidth(13, 30*256);
		rowNum++;
		for(String line: dataList) {
			String strArr[] = line.split("\\|");
			JSONObject json = JSONObject.parseObject(strArr[1]);
			String vesselType = json.getString("vesselType");
			String charteringType = json.getString("charteringType");
			String cargoName = null;
			String duration = null;
			String shipType = json.getString("shipType");
			String loadingPort = null;
			String dischargingPort = null;
			String laycanFrom = null;
			String laycanTo = null;
			String volumeFrom = null;
			String volumeTo = null;
			String flag = null;
			String clazz = null;
			String remark = null;
			if("VC".equals(charteringType)) {
				JSONObject cargo = json.getJSONObject("cargo");
				cargoName = cargo.getString("cargoName");
				volumeFrom = cargo.getString("volumeFrom");
				volumeTo = cargo.getString("volumeTo");
				loadingPort = cargo.getString("loadingPortId");
				dischargingPort = cargo.getString("dischargingPortId");
				JSONObject laycanStart = cargo.getJSONObject("laycanFrom");
				laycanFrom = laycanStart.getString("year") + "/" + laycanStart.getString("monthValue") + "/" + laycanStart.getString("dayOfMonth");
				JSONObject laycanEnd = cargo.getJSONObject("laycanTo");
				laycanTo = laycanEnd.getString("year") + "/" + laycanEnd.getString("monthValue") + "/" + laycanEnd.getString("dayOfMonth");
				remark = cargo.getString("remarks");
			}else {
				JSONObject timeCharter = json.getJSONObject("timeCharter");
				volumeFrom = timeCharter.getString("volumeFrom");
				volumeTo = timeCharter.getString("volumeTo");
				flag = timeCharter.getString("enquiryVesselFlag");
				clazz = timeCharter.getString("enquiryVesselClass");
				duration = timeCharter.getString("duration") + timeCharter.getString("durationUnit");
				JSONObject laycanStart = timeCharter.getJSONObject("laycanFrom");
				laycanFrom = laycanStart.getString("year") + "/" + laycanStart.getString("monthValue") + "/" + laycanStart.getString("dayOfMonth");
				JSONObject laycanEnd = timeCharter.getJSONObject("laycanTo");
				laycanTo = laycanEnd.getString("year") + "/" + laycanEnd.getString("monthValue") + "/" + laycanEnd.getString("dayOfMonth");
				remark = timeCharter.getString("remarks");
			}
			
			
			HSSFRow row = sheet.createRow(rowNum);
			rowNum++;
			cell = row.createCell(0);
			cell.setCellValue(vesselType);
			cell = row.createCell(1);
			cell.setCellValue(charteringType);
			cell = row.createCell(2);
			cell.setCellValue(cargoName);
			cell = row.createCell(3);
			cell.setCellValue(duration);
			cell = row.createCell(4);
			cell.setCellValue(shipType);
			cell = row.createCell(5);
			cell.setCellValue(portMap.get(loadingPort));
			cell = row.createCell(6);
			cell.setCellValue(portMap.get(dischargingPort));
			
			cell = row.createCell(7);
			cell.setCellValue(laycanFrom);
			cell = row.createCell(8);
			cell.setCellValue(laycanTo);
			cell = row.createCell(9);
			cell.setCellValue(volumeFrom + "-" + volumeTo+"MT");
			cell = row.createCell(10);
			cell.setCellValue(flag);
			cell = row.createCell(11);
			cell.setCellValue(clazz);
			cell = row.createCell(12);
			cell.setCellValue(remark);
			cell = row.createCell(13);
			cell.setCellValue(strArr[0]);

		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void export(String path ,String jsonTxt) {
		try {
			FileReader fr = new FileReader(jsonTxt);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			List<String> dataList = new ArrayList<>();
			while ((str = bf.readLine()) != null) {
				dataList.add(str);
			}
			bf.close();
			OutputStream out = new FileOutputStream(path);
			this.export(out, dataList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	static Map<String,String> portMap = new HashMap<>();
	public static void initPort(String file) {
		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			String str;
			// 按行读取字符串
			
			while ((str = bf.readLine()) != null) {
				String[] port = str.split("\t");
				portMap.put(port[0], port[1]);
			}
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		MatchLogExp exp = new MatchLogExp();
		String path = "C:/Users/xiezb/Desktop/log.xls";
		String jsonTxt = "C:/Users/xiezb/Desktop/log.txt";
		String port = "C:/Users/xiezb/Desktop/port.txt";
		initPort(port);
		exp.export(path, jsonTxt);
	}

}
