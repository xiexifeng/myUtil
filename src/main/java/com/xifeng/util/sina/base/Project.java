package com.xifeng.util.sina.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.xifeng.util.StringUtil;
import com.xifeng.util.sina.Constant;
import com.xifeng.util.sina.gen.GenFile;

/**
 * @Description :
 * @Copyright : Sinaif Software Co.,Ltd. All Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @author : schelling
 * @version : 1.0
 * @Date : 2019年8月12日 上午11:25:09
 */
public class Project {

	/**
	 * 项目名称
	 */
	String name;
	/**
	 * 项目所在文件夹路径
	 */
	String path;
	/**
	 * 项目枚举值
	 */
	String productEnum;

	/**
	 * 项目描述
	 */
	String desc;
	
	String sourcePath = "/src/main/java/";
	String resourcesPath = "/src/main/resources/";
	String testPath = "/src/test/java/";
	String basePackage = "com.sinaif.king.finance";
	String[] packages = new String[] { "api", "common", "enums", "model.request.base", "model.response.base",
			"model.callback", "service", "util" };

	public Project() {
	}

	/**
	 * @param name 项目名
	 * @param path 项目所在路径
	 * @param productEnum 项目枚举值
	 * @param desc 项目描述
	 */
	public Project(String name, String path, String productEnum, String desc) {
		this.name = name;
		this.path = path;
		this.productEnum = productEnum;
		this.desc = desc;
	}

	/**
	 * @param name 项目名
	 * @param basePackage 项目包前缀 默认为com.sinaif.king.finance
	 * @param path 项目所在路径
	 * @param productEnum 项目枚举值
	 * @param desc 项目描述
	 */
	public Project(String name, String basePackage, String path, String productEnum, String desc) {
		this.name = name;
		this.basePackage = basePackage;
		this.path = path;
		this.productEnum = productEnum;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getPackages() {
		return packages;
	}

	public void setPackages(String[] packages) {
		this.packages = packages;
	}

	public String getProductEnum() {
		return productEnum;
	}

	public void setProductEnum(String productEnum) {
		this.productEnum = productEnum;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Description: 获取项目根路径
	 * 
	 * @return
	 * @author schelling
	 * @Date 2019年8月12日 下午12:56:38
	 */
	public String getProjectPath() {
		return path + name;
	}

	public String getFullSourcePath() {
		return getProjectPath() + sourcePath + StringUtil.changeToPath(this.basePackage) + Constant.FOLDER_SEPARATOR
				+ this.productEnum.toLowerCase() + Constant.FOLDER_SEPARATOR;
	}

	public String getFullReourcesPath() {
		return getProjectPath() + resourcesPath;
	}

	public String getFullTestPaht() {
		return getProjectPath() + testPath;
	}

	private void genPackages() {
		String sourceFullPath = getFullSourcePath();
		for (String pack : packages) {
			GenFile.genPath(sourceFullPath + StringUtil.changeToPath(pack));
		}
		GenFile.genPath(getFullReourcesPath() + "config");
		GenFile.genPath(getFullTestPaht());
	}

	private void genPom() {
		File pom = new File(getProjectPath() + "/pom.xml");
		try {
			InputStream is = this.getClass().getResourceAsStream("pom-example.xml");
			OutputStream os = new FileOutputStream(pom);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			while (br.ready()) {
				String line = br.readLine();
				if (line.contains("${projectName}")) {
					line = line.replace("${projectName}", this.name);
				}
				if (line.contains("${projectDesc}")) {
					line = line.replace("${projectDesc}", this.desc);
				}
				bw.write(line);
				bw.write(Constant.BR);
			}
			br.close();
			bw.close();
			System.out.println(pom.getAbsolutePath() + " is generated");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void genCapConfig() {
		final String lowerCase = productEnum.toLowerCase();
		File pom = new File(getProjectPath() + resourcesPath + "config" + "/spring-cap-mvc-" + lowerCase + ".xml");
		String processor = basePackage + "." + lowerCase + ".api." + StringUtil.firstUpperCase(lowerCase)
				+ "FinanceProcessor";
		try {
			InputStream is = this.getClass().getResourceAsStream("spring-cap-mvc-project.xml");
			OutputStream os = new FileOutputStream(pom);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			while (br.ready()) {
				String line = br.readLine();
				if (line.contains("${processor}")) {
					line = line.replace("${processor}", processor);
				}
				if (line.contains("${productEnumU}")) {
					line = line.replace("${productEnumU}", this.productEnum);
				}
				if (line.contains("${productEnumL}")) {
					line = line.replace("${productEnumL}", lowerCase);
				}

				bw.write(line);
				bw.write(Constant.BR);
			}
			br.close();
			bw.close();
			System.out.println(pom.getAbsolutePath() + " is generated");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() {
		this.genPackages();
		this.genPom();
		this.genCapConfig();
	}
	
	public void clear() {
		String projectDir = this.getProjectPath();
		GenFile.deleteDirectory(projectDir);
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getTestPath() {
		return testPath;
	}

	public void setTestPath(String testPath) {
		this.testPath = testPath;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
	
	public String getReqHeaderName() {
		return StringUtil.firstUpperCase(this.productEnum.toLowerCase()) + "ReqHeader";
	}
	public String getReqHeaderPackage() {
		return this.getBasePackage() + "." + this.getProductEnum().toLowerCase() + ".model.request.base";
	}
	
	public String getResHeaderName() {
		return StringUtil.firstUpperCase(this.productEnum.toLowerCase()) + "ResHeader";
	}
	
	public String getResHeaderPackage() {
		return this.getBasePackage() + "." + this.getProductEnum().toLowerCase() + ".model.response.base";
	}
	
	public String getTransCodeName() {
		return StringUtil.firstUpperCase(this.productEnum.toLowerCase()) + "TransCode";
	}
	public String getTransCodePackage() {
		return this.getBasePackage() + "." + this.getProductEnum().toLowerCase() + ".enums";
	}
	
	public String getBaseReqName() {
		return StringUtil.firstUpperCase(this.productEnum.toLowerCase()) + "BaseReq";
	}
	
	public String getBaseReqPackage() {
		return this.getReqHeaderPackage();
	}
	
	public String getReqPackage() {
		return this.getBasePackage() + "." + this.getProductEnum().toLowerCase() + ".model.request";
	}
	
	public String getResPackage() {
		return this.getBasePackage() + "." + this.getProductEnum().toLowerCase() + ".model.response";
	}
	
	public String getProjectPrefix() {
		return StringUtil.firstUpperCase(this.getProductEnum().toLowerCase());
	}
}
