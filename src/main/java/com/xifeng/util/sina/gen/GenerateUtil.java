package com.xifeng.util.sina.gen;

import static com.xifeng.util.sina.Constant.ATTR_RETRACT;
import static com.xifeng.util.sina.Constant.BLANK;
import static com.xifeng.util.sina.Constant.BR;
import static com.xifeng.util.sina.Constant.EQUAL;
import static com.xifeng.util.sina.Constant.METHOD_RETRACT;
import static com.xifeng.util.sina.Constant.SEMICOLON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpMethod;

import com.xifeng.util.StringUtil;
import com.xifeng.util.sina.base.EnumParam;
import com.xifeng.util.sina.base.FileDescription;
import com.xifeng.util.sina.base.Group;
import com.xifeng.util.sina.base.Parameter;
import com.xifeng.util.sina.base.Project;
import com.xifeng.util.sina.base.TransCode;

/**
 * @Description :
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 上午11:20:41
 */
public class GenerateUtil {

	static Map<String, String> IMPORT_MAP = new HashMap<String, String>();
	static {
		IMPORT_MAP.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
		IMPORT_MAP.put("String", "java.lang.String");
		IMPORT_MAP.put("Long", "java.lang.Long");
		IMPORT_MAP.put("long", "java.lang.Long");
		IMPORT_MAP.put("Integer", "java.lang.Integer");
		IMPORT_MAP.put("int", "java.lang.Integer");
		IMPORT_MAP.put("Double", "java.lang.Double");
		IMPORT_MAP.put("double", "java.lang.Double");

		IMPORT_MAP.put("BigDecimal", "java.math.BigDecimal");
		IMPORT_MAP.put("array", "java.util.List");
		IMPORT_MAP.put("List", "java.util.List");
		
		IMPORT_MAP.put("Object", "java.lang.Object");
		IMPORT_MAP.put("JSONObject", "com.alibaba.fastjson.JSONObject");
		IMPORT_MAP.put("JSONArray", "com.alibaba.fastjson.JSONArray");
		
	}

	/**
	 * Description: 生成基础类
	 * 
	 * @author schelling
	 * @Date 2019年8月12日 上午11:21:02
	 */
	public static void gernerateBase(Project pro, FileDescription fileDesc) {
//		gernerateReqHeader(pro, fileDesc);
//		gernerateBaseReq(pro, fileDesc);
		gernerateBaseReqForNew(pro, fileDesc);
		gernerateResHeader(pro, fileDesc);

	}

	private static void gernerateReqHeader(Project pro, FileDescription fileDesc) {
		fileDesc.setDescription("请求头");
		StringBuilder reqHeader = new StringBuilder(1000);
		String packagePath = pro.getReqHeaderPackage();
		String className = pro.getReqHeaderName();
		reqHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		reqHeader.append(BR);
		reqHeader.append("import java.io.Serializable;").append(BR);// import;
		reqHeader.append("import com.alibaba.fastjson.annotation.JSONField;").append(BR);// import;
		reqHeader.append("import com.fasterxml.jackson.annotation.JsonIgnore;").append(BR);// import;

		reqHeader.append(BR);
		reqHeader.append(fileDesc.genFileDes());
		// BwqbReqHeader
		reqHeader.append("public class ").append(className).append(" implements Serializable {").append(BR);

		reqHeader.append(BR);
		reqHeader.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		reqHeader.append(BR);

		String[] attrs = new String[] { "userId", "orderId", "terminalId", "productId" };
		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		for (String attr : attrs) {
			reqAttr.append(METHOD_RETRACT).append("@JsonIgnore").append(BR);
			reqAttr.append(METHOD_RETRACT).append("@JSONField(serialize = false)").append(BR);
			reqAttr.append(METHOD_RETRACT).append("private String ").append(attr).append(SEMICOLON).append(BR);

			reqAttrGetAndSet.append(GenFile.genSet(attr, "String"));

			reqAttrGetAndSet.append(GenFile.genGet(attr, "String"));
		}
		reqHeader.append(reqAttr).append(BR).append(reqAttrGetAndSet);
		reqHeader.append(BR);
		reqHeader.append("}");
		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.request.base.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, reqHeader.toString());
	}

	private static void gernerateResHeader(Project pro, FileDescription fileDesc) {
		fileDesc.setDescription("请求返回头");
		StringBuilder resHeader = new StringBuilder(1000);
		String packagePath = pro.getResHeaderPackage();
		String className = pro.getResHeaderName();
		resHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		resHeader.append(BR);
		resHeader.append("import java.io.Serializable;").append(BR);// import;

		resHeader.append(BR);
		resHeader.append(fileDesc.genFileDes());
		// BwqbResHeader
		resHeader.append("public class ").append(className).append(" implements Serializable {").append(BR);

		resHeader.append(BR);
		resHeader.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		resHeader.append(BR);

		resHeader.append(BR);
		resHeader.append("}");
		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.response.base.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, resHeader.toString());
	}

	public static void gernerateTransCode(Project pro, FileDescription fileDesc, List<TransCode> transCodeList,
			String successCode) {
		fileDesc.setDescription("请求交易code枚举类");
		StringBuilder reqHeader = new StringBuilder(1000);
		String packagePath = pro.getTransCodePackage();
		String className = pro.getTransCodeName();
		reqHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		reqHeader.append(BR);
		reqHeader.append("import org.springframework.http.HttpMethod;").append(BR);// import;
		reqHeader.append("import com.sinaif.king.model.finance.enums.BaseTransCode;").append(BR);// import;

		reqHeader.append(BR);
		reqHeader.append(fileDesc.genFileDes());
		// BwqbReqHeader
		reqHeader.append("public enum ").append(className).append(" implements BaseTransCode {").append(BR);

		reqHeader.append(BR);

		List<Parameter> params = new ArrayList<>();
		params.add(new Parameter("path", "String", true, "请求路径"));
		params.add(new Parameter("code", "String", true, "请求标识"));
		params.add(new Parameter("method", "HttpMethod", true, "请求方式"));
		params.add(new Parameter("desc", "String", true, "请求接口描述"));

		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		StringBuilder constructor1 = new StringBuilder();
		StringBuilder constructorParam = new StringBuilder();
		StringBuilder constructor2Param = new StringBuilder();
		StringBuilder constructorSet = new StringBuilder();
		StringBuilder constructor2 = new StringBuilder();
		for (Parameter param : params) {
			reqAttr.append(GenFile.genAttr(param));
			reqAttrGetAndSet.append(GenFile.genSet(param));
			reqAttrGetAndSet.append(GenFile.genGet(param));
			constructorParam.append(param.getAttrType()).append(BLANK).append(param.getAttr()).append(",");
			constructorSet.append(ATTR_RETRACT).append("this.").append(param.getAttr()).append(EQUAL)
					.append(param.getAttr()).append(SEMICOLON).append(BR);
			if (!param.getAttr().equals("method")) {
				constructor2Param.append(param.getAttrType()).append(BLANK).append(param.getAttr()).append(",");
			}
		}
		constructor1.append(METHOD_RETRACT).append(className).append("(");
		constructor1.append(constructorParam.substring(0, constructorParam.length() - 1)).append(") {").append(BR);
		constructor1.append(constructorSet).append(BR).append(METHOD_RETRACT).append("}").append(BR);

		constructor2.append(METHOD_RETRACT).append(className).append("(");
		constructor2.append(constructor2Param.substring(0, constructor2Param.length() - 1)).append(") {").append(BR);
		constructor2.append(ATTR_RETRACT).append("this(path, code, HttpMethod.POST, desc);").append(BR)
				.append(METHOD_RETRACT).append("}").append(BR);

		StringBuilder transCodeEnum = new StringBuilder();
		for (TransCode transCode : transCodeList) {
			if (transCode.getMethod().equals(HttpMethod.POST.name())) {
				transCodeEnum.append(METHOD_RETRACT).append(transCode.getCode().toUpperCase()).append("(\"")
						.append(transCode.getPath()).append("\",\"").append(transCode.getCode()).append("\",\"")
						.append(transCode.getDesc()).append("\"),").append(BR);
			} else {
				transCodeEnum.append(METHOD_RETRACT).append(transCode.getCode().toUpperCase()).append("(\"")
						.append(transCode.getPath()).append("\",\"").append(transCode.getCode())
						.append("\",HttpMethod.").append(transCode.getMethod()).append(",\"")
						.append(transCode.getDesc()).append("\"),").append(BR);
			}

		}
		reqHeader.append(transCodeEnum).append(METHOD_RETRACT).append(SEMICOLON).append(BR).append(constructor1)
				.append(BR).append(constructor2).append(BR).append(reqAttr).append(BR).append(reqAttrGetAndSet)
				.append(BR);
		reqHeader.append(METHOD_RETRACT).append("@Override").append(BR);
		reqHeader.append(METHOD_RETRACT).append("public boolean ifBusinessSuccess(String code) {").append(BR);
		reqHeader.append(ATTR_RETRACT).append("return \"").append(successCode).append("\".equals(code);").append(BR);
		reqHeader.append(METHOD_RETRACT).append("}").append(BR);

		reqHeader.append("}");
		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("enums.") + className + ".java";
		GenFile.writeFile(javaFileName, reqHeader.toString());
	}

	private static void gernerateBaseReq(Project pro, FileDescription fileDesc) {
		fileDesc.setDescription("请求基类");
		StringBuilder reqHeader = new StringBuilder(1000);
		String packagePath = pro.getBaseReqPackage();
		String className = pro.getBaseReqName();
		String transCodeName = pro.getTransCodeName();
		String headerName = pro.getReqHeaderName();
		reqHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		reqHeader.append(BR);
		reqHeader.append("import java.io.Serializable;").append(BR);// import;
		reqHeader.append("import com.alibaba.fastjson.annotation.JSONField;").append(BR);// import;
		reqHeader.append("import com.fasterxml.jackson.annotation.JsonIgnore;").append(BR);// import;
		reqHeader.append("import com.sinaif.king.finance.").append(pro.getProductEnum().toLowerCase()).append(".enums.")
				.append(transCodeName).append(SEMICOLON).append(BR);

		reqHeader.append(BR);
		reqHeader.append(fileDesc.genFileDes());
		// BwqbReqHeader
		reqHeader.append("public abstract class ").append(className).append(" implements Serializable {").append(BR);

		reqHeader.append(BR);
		reqHeader.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		reqHeader.append(BR);

		String[] attrs = new String[] { "header" };

		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		for (String attr : attrs) {
			reqAttr.append(METHOD_RETRACT).append("@JsonIgnore").append(BR);
			reqAttr.append(METHOD_RETRACT).append("@JSONField(serialize = false)").append(BR);
			reqAttr.append(METHOD_RETRACT).append("private ").append(headerName).append(BLANK).append(attr)
					.append(SEMICOLON).append(BR);

			reqAttrGetAndSet.append(GenFile.genSet(attr, headerName));

			reqAttrGetAndSet.append(GenFile.genGet(attr, headerName));
		}
		StringBuilder transcode = new StringBuilder();

		transcode.append(METHOD_RETRACT).append("@JsonIgnore").append(BR);
		transcode.append(METHOD_RETRACT).append("@JSONField(serialize = false)").append(BR);
		transcode.append(METHOD_RETRACT).append("public abstract ").append(transCodeName).append(" getTransCode();")
				.append(BR);
		reqHeader.append(reqAttr).append(BR).append(transcode).append(BR).append(reqAttrGetAndSet);
		reqHeader.append(BR);
		reqHeader.append("}");
		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.request.base.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, reqHeader.toString());
	}
	
	private static void gernerateBaseReqForNew(Project pro, FileDescription fileDesc) {
		fileDesc.setDescription("请求基类");
		StringBuilder reqHeader = new StringBuilder(1000);
		String packagePath = pro.getBaseReqPackage();
		String className = pro.getBaseReqName();
		String transCodeName = pro.getTransCodeName();
		String headerName = pro.getReqHeaderName();
		reqHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		reqHeader.append(BR);
//		reqHeader.append("import java.io.Serializable;").append(BR);// import;
		reqHeader.append("import com.alibaba.fastjson.annotation.JSONField;").append(BR);
		reqHeader.append("import com.fasterxml.jackson.annotation.JsonIgnore;").append(BR);
		reqHeader.append("import com.sinaif.king.finance.basic.enums.SendApiTypeEnum;").append(BR);
		reqHeader.append("import com.sinaif.king.finance.basic.model.api.BaseApiReqModel;").append(BR);
		reqHeader.append("import com.sinaif.king.finance.").append(pro.getProductEnum().toLowerCase()).append(".enums.")
				.append(transCodeName).append(SEMICOLON).append(BR);

		reqHeader.append(BR);
		reqHeader.append(fileDesc.genFileDes());
		// BwqbReqHeader
		reqHeader.append("public abstract class ").append(className).append(" extends BaseApiReqModel {").append(BR);

		reqHeader.append(BR);
		reqHeader.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		reqHeader.append(BR);

		
		
		StringBuilder constructor = new StringBuilder(1000);
		StringBuilder constructorSet = new StringBuilder();
		constructor.append(METHOD_RETRACT).append("public ").append(className).append("(String userId, String productId, String terminalId) {").append(BR);
		constructorSet.append(ATTR_RETRACT).append("super(userId, productId, terminalId);").append(BR);
		constructor.append(constructorSet).append(METHOD_RETRACT).append("}").append(BR);
		constructor.append(BR);
		
		
		
		StringBuilder transcode = new StringBuilder();

		transcode.append(METHOD_RETRACT).append("@JsonIgnore").append(BR);
		transcode.append(METHOD_RETRACT).append("@JSONField(serialize = false)").append(BR);
		transcode.append(METHOD_RETRACT).append("public abstract ").append(transCodeName).append(" getTransCode();")
				.append(BR);
		
		transcode.append(BR);
		transcode.append(METHOD_RETRACT).append("@Override").append(BR);
		transcode.append(METHOD_RETRACT).append("public SendApiTypeEnum iGetSendType() {").append(BR);
		transcode.append(ATTR_RETRACT).append("return SendApiTypeEnum.JSON_WITH_HEAD;").append(BR);
		transcode.append(METHOD_RETRACT).append("}").append(BR);
		
		transcode.append(BR);
		transcode.append(METHOD_RETRACT).append("@Override").append(BR);
		transcode.append(METHOD_RETRACT).append("public String iGetTransCode() {").append(BR);
		transcode.append(ATTR_RETRACT).append("return getTransCode().getCode();").append(BR);
		transcode.append(METHOD_RETRACT).append("}").append(BR);
		
		reqHeader.append(constructor).append(BR).append(transcode).append(BR);
		reqHeader.append(BR);
		reqHeader.append("}");
		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.request.base.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, reqHeader.toString());
	}
	
	public static String getTransCodeForClassName(Project pro,TransCode transCode) {
		if(transCode.getCode().contains("_")) {
			return pro.getProjectPrefix() + StringUtil.firstUpperCase(StringUtil.underscoreToCamelCase(transCode.getCode()));
		}
		return pro.getProjectPrefix() + StringUtil.firstUpperCase(transCode.getCode());
		
	}

	/**
	 * Description: 生成请求类
	 * 
	 * @author schelling
	 * @Date 2019年8月12日 上午11:22:16
	 */
	public static void gernerateRequest(Project pro, FileDescription fileDesc, TransCode transCode,
			List<Parameter> paramList) {
		if(CollectionUtils.isEmpty(paramList)) {
			return;
		}
		fileDesc.setDescription(transCode.getDesc() + "请求实体类");
		String packagePath = pro.getReqPackage();
		String className = getTransCodeForClassName(pro, transCode) + "Req";
		String transCodeName = pro.getTransCodeName();
		StringBuilder request = new StringBuilder(1000);
		StringBuilder requestHeader = new StringBuilder(1000);
		StringBuilder requestBody = new StringBuilder(1000);
		requestHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		requestHeader.append(BR);
//		requestHeader.append("import org.hibernate.validator.constraints.NotBlank;").append(BR);// import;
		if (!transCode.isPojo()) {
			requestHeader.append("import ").append(pro.getBaseReqPackage()).append(".").append(pro.getBaseReqName())
			.append(SEMICOLON).append(BR);
			requestHeader.append("import ").append(pro.getTransCodePackage()).append(".").append(transCodeName)
			.append(SEMICOLON).append(BR);
		}
		

		Map<String, String> importMap = new HashMap<String, String>();
//		importMap.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
		importMap.put("String", "java.lang.String");
		importMap.put("Long", "java.lang.Long");
		importMap.put("long", "java.lang.Long");
		importMap.put("Integer", "java.lang.Integer");
		importMap.put("int", "java.lang.Integer");
		importMap.put("Long", "java.lang.Long");
		importMap.put("long", "java.lang.Long");
		importMap.put("Double", "java.lang.Double");
		importMap.put("double", "java.lang.Double");
		importMap.put("Object", "java.lang.Object");

		requestBody.append(BR);
		requestBody.append(fileDesc.genFileDes());

		requestBody.append("public class ").append(className);
		if (transCode.isPojo()) {
			requestHeader.append("import java.io.Serializable;").append(BR);
			requestBody.append(" implements Serializable {").append(BR);
		} else {
			requestBody.append(" extends ").append(pro.getBaseReqName()).append(" {").append(BR);
		}

		requestBody.append(BR);
		requestBody.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		requestBody.append(BR);

		if (!transCode.isPojo()) { // Override
			requestBody.append(METHOD_RETRACT).append("@Override").append(BR);
			requestBody.append(METHOD_RETRACT).append("public ").append(transCodeName).append(" getTransCode() {")
					.append(BR);
			requestBody.append(ATTR_RETRACT).append("return ").append(transCodeName).append(".")
					.append(transCode.getCode().toUpperCase()).append(SEMICOLON).append(BR);
			requestBody.append(METHOD_RETRACT).append("}").append(BR);
		}
		
		StringBuilder constructor = new StringBuilder(1000);
		StringBuilder constructorSet = new StringBuilder();
		if (!transCode.isPojo()) {
			constructor.append(METHOD_RETRACT).append("public ").append(className).append("(String userId, String productId, String terminalId) {").append(BR);
			constructorSet.append(ATTR_RETRACT).append("super(userId, productId, terminalId);").append(BR);
			constructor.append(constructorSet).append(METHOD_RETRACT).append("}").append(BR);
			constructor.append(BR);
		}

		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		for (Parameter param : paramList) {
			if (importMap.get(param.getAttrType()) == null) {
				if(param.getAttrType().contains("List")) {
					if(importMap.get("List") == null) {
						requestHeader.append("import ").append(IMPORT_MAP.get("List"))
						.append(SEMICOLON).append(BR);
						importMap.put("List", IMPORT_MAP.get("List"));
					}
				}else {
					if (IMPORT_MAP.get(param.getAttrType()) != null) {
						requestHeader.append("import ").append(IMPORT_MAP.get(param.getAttrType())).append(SEMICOLON)
								.append(BR);
						importMap.put(param.getAttrType(), IMPORT_MAP.get(param.getAttrType()));
					} else {
						System.out.println(param.toString() + " error!!!!");
						requestHeader.append("import ").append(packagePath).append(".").append(param.getAttrType())
								.append(SEMICOLON).append(BR);
						importMap.put(param.getAttrType(), packagePath + param.getAttrType());
					}
				}
				
			}
			if(!param.isEmpty()&&importMap.get("NotBlank")==null) {
				requestHeader.append("import org.hibernate.validator.constraints.NotBlank;").append(BR);// import;
				importMap.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
			}
			reqAttr.append(GenFile.genAttr(param));
			reqAttrGetAndSet.append(GenFile.genSet(param));
			reqAttrGetAndSet.append(GenFile.genGet(param));
			
		}

		requestBody.append(reqAttr).append(BR).append(constructor).append(BR).append(reqAttrGetAndSet);
		requestBody.append(BR);
		requestBody.append("}");

		request.append(requestHeader).append(requestBody);

		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.request.") + className + ".java";
		GenFile.writeFile(javaFileName, request.toString());

	}

	/**
	 * Description: 生成返回类
	 * 
	 * @author schelling
	 * @Date 2019年8月12日 上午11:22:52
	 */
	public static void gernerateResponse(Project pro, FileDescription fileDesc, TransCode transCode,
			List<Parameter> paramList) {
		if(CollectionUtils.isEmpty(paramList)) {
			return;
		}
		fileDesc.setDescription(transCode.getDesc() + "请求返回实体类");
		String packagePath = pro.getResPackage();
		String className = getTransCodeForClassName(pro, transCode) + "Res";
		StringBuilder request = new StringBuilder(1000);
		StringBuilder requestHeader = new StringBuilder(1000);
		StringBuilder requestBody = new StringBuilder(1000);
		requestHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		requestHeader.append(BR);
//		requestHeader.append("import org.hibernate.validator.constraints.NotBlank;").append(BR);// import;
		if (!transCode.isPojo()) {
			requestHeader.append("import ").append(pro.getResHeaderPackage()).append(".").append(pro.getResHeaderName())
					.append(SEMICOLON).append(BR);
		}

		Map<String, String> importMap = new HashMap<String, String>();
//		importMap.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
		importMap.put("String", "java.lang.String");
		importMap.put("Long", "java.lang.Long");
		importMap.put("long", "java.lang.Long");
		importMap.put("Integer", "java.lang.Integer");
		importMap.put("int", "java.lang.Integer");
		importMap.put("Long", "java.lang.Long");
		importMap.put("long", "java.lang.Long");
		importMap.put("Double", "java.lang.Double");
		importMap.put("double", "java.lang.Double");
		importMap.put("Object", "java.lang.Object");

		requestBody.append(BR);
		requestBody.append(fileDesc.genFileDes());

		requestBody.append("public class ").append(className);
		if (transCode.isPojo()) {
			requestHeader.append("import java.io.Serializable;").append(BR);
			requestBody.append(" implements Serializable {").append(BR);
		} else {
			requestBody.append(" extends ").append(pro.getResHeaderName()).append(" {").append(BR);
		}

		requestBody.append(BR);
		requestBody.append(METHOD_RETRACT).append("private static final long serialVersionUID = 1L").append(SEMICOLON)
				.append(BR);
		requestBody.append(BR);

		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		if(!(paramList.size() == 1 && paramList.get(0).getAttr().equals("empty"))) {
			for (Parameter param : paramList) {
				if (importMap.get(param.getAttrType()) == null) {
					if(param.getAttrType().contains("List")) {
						if(importMap.get("List") == null) {
							requestHeader.append("import ").append(IMPORT_MAP.get("List"))
							.append(SEMICOLON).append(BR);
							importMap.put("List", IMPORT_MAP.get("List"));
						}
						
					}else {
						if (IMPORT_MAP.get(param.getAttrType()) != null) {
							requestHeader.append("import ").append(IMPORT_MAP.get(param.getAttrType())).append(SEMICOLON)
									.append(BR);
							importMap.put(param.getAttrType(), IMPORT_MAP.get(param.getAttrType()));
						} else {
							System.out.println(param.toString() + " error!!!!");
							requestHeader.append("import ").append(packagePath).append(".").append(param.getAttrType())
									.append(SEMICOLON).append(BR);
							importMap.put(param.getAttrType(), packagePath + param.getAttrType());
						}
					}
				}
				if(!param.isEmpty()&&importMap.get("NotBlank")==null) {
					requestHeader.append("import org.hibernate.validator.constraints.NotBlank;").append(BR);// import;
					importMap.put("NotBlank", "org.hibernate.validator.constraints.NotBlank");
				}
				
				reqAttr.append(GenFile.genAttr(param));
				reqAttrGetAndSet.append(GenFile.genSet(param));
				reqAttrGetAndSet.append(GenFile.genGet(param));
			}
		}
		
		

		requestBody.append(reqAttr).append(BR).append(reqAttrGetAndSet);
		requestBody.append(BR);
		requestBody.append("}");

		request.append(requestHeader).append(requestBody);

		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("model.response.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, request.toString());
	}
	
	public static void gernerateEnum(Project pro, FileDescription fileDesc, TransCode transCode,
			List<EnumParam> paramList) {
		if(CollectionUtils.isEmpty(paramList)) {
			return;
		}
		fileDesc.setDescription(transCode.getDesc() + "枚举类");
		String packagePath = pro.getTransCodePackage();
		String className = getTransCodeForClassName(pro, transCode);
		StringBuilder request = new StringBuilder(1000);
		StringBuilder requestHeader = new StringBuilder(1000);
		StringBuilder requestBody = new StringBuilder(1000);
		requestHeader.append("package ").append(packagePath).append(SEMICOLON).append(BR);// package

		requestHeader.append(BR);
		requestHeader.append("import java.util.HashMap;").append(BR);// import;
		requestHeader.append("import java.util.Map;").append(BR);


		requestBody.append(BR);
		requestBody.append(fileDesc.genFileDes());

		requestBody.append("public enum ").append(className).append(" {").append(BR);

		requestBody.append(BR);
		
		List<Parameter> params = new ArrayList<>();
		params.add(new Parameter("code", "String", true, "对应的code"));
		params.add(new Parameter("codeDesc", "String", true, "对应code的描述"));
		if(StringUtil.isNotEmpty(paramList.get(0).getDesc())) {
			params.add(new Parameter("desc", "String", true, "补充描述"));
		}
		
		StringBuilder reqAttr = new StringBuilder(1000);
		StringBuilder reqAttrGetAndSet = new StringBuilder(1000);
		StringBuilder constructor = new StringBuilder(1000);
		StringBuilder constructorParam = new StringBuilder();
		StringBuilder constructorSet = new StringBuilder();
		for (Parameter param : params) {
			reqAttr.append(GenFile.genAttr(param));
			reqAttrGetAndSet.append(GenFile.genGet(param));
			constructorParam.append(param.getAttrType()).append(BLANK).append(param.getAttr()).append(", ");
			constructorSet.append(ATTR_RETRACT).append("this.").append(param.getAttr()).append(EQUAL)
					.append(param.getAttr()).append(SEMICOLON).append(BR);
			
		}
		constructor.append(METHOD_RETRACT).append(className).append("(");
		constructor.append(constructorParam.substring(0, constructorParam.length() - 2)).append(") {").append(BR);
		constructor.append(constructorSet).append(BR).append(METHOD_RETRACT).append("}").append(BR);
		constructor.append(BR);
		
		constructor.append(METHOD_RETRACT).append("private static final Map<String, ").append(className).append("> mappings = new HashMap<>(8);").append(BR);
		
		constructor.append(BR);
		constructor.append(METHOD_RETRACT).append("static {").append(BR);
		constructor.append(ATTR_RETRACT).append("for (").append(className).append(" value : values()) {").append(BR);
		constructor.append(METHOD_RETRACT).append(ATTR_RETRACT).append("mappings.put(value.name(), value);").append(BR);
		constructor.append(ATTR_RETRACT).append("}").append(BR);
		constructor.append(METHOD_RETRACT).append("}").append(BR);
		
		constructor.append(BR);
		constructor.append(METHOD_RETRACT).append("public static ").append(className).append(" resolve(String method) {").append(BR);
		constructor.append(ATTR_RETRACT).append("return (method != null ? mappings.get(method) : null);").append(BR);
		constructor.append(METHOD_RETRACT).append("}").append(BR);
		
		constructor.append(BR);
		constructor.append(METHOD_RETRACT).append("public boolean matches(String method) {").append(BR);
		constructor.append(ATTR_RETRACT).append("return (this == resolve(method));").append(BR);
		constructor.append(METHOD_RETRACT).append("}").append(BR);
		
		StringBuilder enums = new StringBuilder(1000);
		for(EnumParam enumParam : paramList) {
			enums.append(METHOD_RETRACT).append(enumParam.getType()).append("(")//type
			.append("\"").append(enumParam.getCode()).append("\"")//code
			.append(", \"").append(enumParam.getCodeDesc()).append("\"");//codeDesc
			if(StringUtil.isNotEmpty(enumParam.getDesc())) {
				enums.append(", \"").append(enumParam.getDesc()).append("\"");//desc
			}
			enums.append("),").append(BR);
		}
		enums.append(METHOD_RETRACT).append(";").append(BR);
		
		requestBody.append(enums).append(BR)
		.append(constructor).append(BR)
		.append(reqAttr).append(BR)
		.append(reqAttrGetAndSet);
		requestBody.append(BR);
		requestBody.append("}");

		request.append(requestHeader).append(requestBody);

		String javaFileName = pro.getFullSourcePath() + StringUtil.changeToPath("enums.") + className
				+ ".java";
		GenFile.writeFile(javaFileName, request.toString());
	}

	/**
	 * Description: 生成服务类
	 * 
	 * @author schelling
	 * @Date 2019年8月12日 上午11:23:12
	 */
	public static void gernerateService() {

	}

	public static void gernerateFromExcel(Project pro, FileDescription fileDesc, String excelPath,String successCode) {

		try {
			InputStream input = new FileInputStream(excelPath); // 建立输入流
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			TransCode transCode = null;
			List<Parameter> reqParamList = null;
			List<Parameter> resParamList = null;
			int rowNum = 0;
			if(!sheet.getRow(0).getCell(0).getStringCellValue().equals("接口名称")) {
				return;
			}
			List<Group> groupList = new ArrayList<>();
			String path = null;
			String code = null;
			String method = null;
			String desc = null;
			String pojo = null;
			
			String attr = null;
			String attrType = null;
			boolean empty = false;
			String paramDesc = null;
			Group currentGroup = null;
			while(rowNum < lastRowNum){
				Row row = sheet.getRow(rowNum);
				Cell cell = row.getCell(0);
				String cellValue = cell.getStringCellValue();
				if ("接口名称".equals(cellValue)) {
					rowNum += 1;
					row = sheet.getRow(rowNum);
					currentGroup = new Group();
					code = row.getCell(0).getStringCellValue();
					path = row.getCell(1).getStringCellValue();
					method = row.getCell(2).getStringCellValue();
					desc = row.getCell(3).getStringCellValue();
					pojo = row.getCell(4).getStringCellValue();
					transCode = new TransCode(path, code, method, desc);
					transCode.setPojo("是".equals(pojo));
					rowNum += 1;
					currentGroup.setTransCode(transCode);
					groupList.add(currentGroup);
					continue;
				}
				if ("请求参数".equals(cellValue)) {
					rowNum += 1;
					row = sheet.getRow(rowNum);
					if ("参数名".equals(row.getCell(0).getStringCellValue())) {
						rowNum += 1;
					}
					reqParamList = new ArrayList<>();
					int i = rowNum;
					for(;i < lastRowNum; i++) {
						row = sheet.getRow(i);
						String tempValue = row.getCell(0).getStringCellValue();
						if("接口名称".equals(tempValue)
								||"请求参数".equals(tempValue)||"响应参数".equals(tempValue)) {
							rowNum = i ;
							break;
						}
						attr = row.getCell(0).getStringCellValue();
						attrType = row.getCell(1).getStringCellValue();
						empty = !"是".equals(row.getCell(2).getStringCellValue());
						paramDesc = row.getCell(3).getStringCellValue();
						reqParamList.add(new Parameter(attr, attrType, empty, paramDesc));
					}
					currentGroup.setReqParamList(reqParamList);
					rowNum = i ;
					continue;
				}
				if ("响应参数".equals(cellValue)) {
					rowNum += 1;
					row = sheet.getRow(rowNum);
					if ("参数名".equals(row.getCell(0).getStringCellValue())) {
						rowNum += 1;
					}
					resParamList = new ArrayList<>();
					int i = rowNum;
					for(;i < lastRowNum; i++) {
						row = sheet.getRow(i);
						String tempValue = row.getCell(0).getStringCellValue();
						if("接口名称".equals(tempValue)
								||"请求参数".equals(tempValue)||"响应参数".equals(tempValue)) {
							rowNum = i ;
							break;
						}
						attr = row.getCell(0).getStringCellValue();
						attrType = row.getCell(1).getStringCellValue();
						empty = !"是".equals(row.getCell(2).getStringCellValue());
						paramDesc = row.getCell(3).getStringCellValue();
						resParamList.add(new Parameter(attr, attrType, empty, paramDesc));
					}
					currentGroup.setResParamList(resParamList);
					rowNum = i ;
					continue;
				}
			}
			List<TransCode> transCodeList = new ArrayList<>();
			for(Group group: groupList) {
				if(!group.getTransCode().isPojo()) {
					transCodeList.add(group.getTransCode());
				}
				GenerateUtil.gernerateRequest(pro, fileDesc, group.getTransCode(), group.getReqParamList());
				GenerateUtil.gernerateResponse(pro, fileDesc, group.getTransCode(), group.getResParamList());
			}
			GenerateUtil.gernerateTransCode(pro, fileDesc, transCodeList, successCode);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void gernerateEnumFromExcel(Project pro, FileDescription fileDesc, String excelPath) {

		try {
			InputStream input = new FileInputStream(excelPath); // 建立输入流
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(1);
			int lastRowNum = sheet.getLastRowNum();
			TransCode transCode = null;
			List<EnumParam> enumParamList = null;
			int rowNum = 0;
			if(!sheet.getRow(0).getCell(0).getStringCellValue().equals("枚举类")) {
				return;
			}
			List<Group> groupList = new ArrayList<>();
			String path = null;
			String code = null;
			String method = null;
			String desc = null;
			
			String type = null;
			String enumCode = null;
			String codeDesc = null;
			String enumDesc = null;
			Group currentGroup = null;
			while(rowNum < lastRowNum){
				Row row = sheet.getRow(rowNum);
				Cell cell = row.getCell(0);
				String cellValue = cell.getStringCellValue();
				if ("枚举类".equals(cellValue)) {
					rowNum += 1;
					row = sheet.getRow(rowNum);
					currentGroup = new Group();
					code = row.getCell(0).getStringCellValue();
					desc = row.getCell(1).getStringCellValue();
					transCode = new TransCode(path, code, method, desc);
					rowNum += 1;
					currentGroup.setTransCode(transCode);
					groupList.add(currentGroup);
					continue;
				}
				if ("枚举值".equals(cellValue)) {
					rowNum += 1;
					row = sheet.getRow(rowNum);
					enumParamList = new ArrayList<>();
					int i = rowNum;
					for(;i < lastRowNum; i++) {
						row = sheet.getRow(i);
						String tempValue = row.getCell(0).getStringCellValue();
						if("枚举类".equals(tempValue)) {
							rowNum = i ;
							break;
						}
						type = row.getCell(0).getStringCellValue();
						enumCode = row.getCell(1).getStringCellValue();
						codeDesc = row.getCell(2).getStringCellValue();
						if(row.getCell(3) !=null) {
							enumDesc = row.getCell(3).getStringCellValue();
						}
						enumParamList.add(new EnumParam(type, enumCode, codeDesc, enumDesc));
					}
					currentGroup.setEnumParamList(enumParamList);
					rowNum = i ;
					continue;
				}
			}
			for(Group group: groupList) {
				GenerateUtil.gernerateEnum(pro, fileDesc, group.getTransCode(), group.getEnumParamList());
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
