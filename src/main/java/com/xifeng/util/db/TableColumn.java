/**
 * Project Name:MyUtil <br>
 * File Name:TableColumn.java <br>
 * Package Name:com.xifeng.util.db <br>
 * @author xiezbmf
 * Date:2018年5月28日上午11:17:59 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.db;
/**
 * ClassName: TableColumn <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日上午11:17:59 <br>
 * @version
 * @since JDK 1.8
 */
public class TableColumn {
	/**
	 * columnName:列名.
	 */
	String columnName;
	/**
	 * typeName:类型名.
	 */
	String typeName;
	/**
	 * columnSize:列长度.
	 */
	String columnSize;
	/**
	 * isNullable:是否可以为空.
	 */
	String isNullable;
	/**
	 * decimalDigits:数字 精确值.
	 */
	String decimalDigits;
	/**
	 * columnDef:列默认值.
	 */
	String columnDef;
	
	String remark;
	
	public TableColumn(){
		
	}
	public TableColumn(String columnName,String typeName,String columnSize,String isNullable,String decimalDigits,String columnDef,String remark) {
		this.columnName = columnName;
		this.typeName = typeName;
		this.columnSize = columnSize;
		this.isNullable = isNullable;
		this.decimalDigits = decimalDigits;
		this.columnDef = columnDef;
		this.remark = remark;
		
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getDecimalDigits() {
		return decimalDigits;
	}
	public void setDecimalDigits(String decimalDigits) {
		this.decimalDigits = decimalDigits;
	}
	public String getColumnDef() {
		return columnDef;
	}
	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "TableColumn [columnName=" + columnName + ", typeName=" + typeName + ", columnSize=" + columnSize
				+ ", isNullable=" + isNullable + ", decimalDigits=" + decimalDigits + ", columnDef=" + columnDef
				+ ", remark=" + remark + "]";
	}
}

	