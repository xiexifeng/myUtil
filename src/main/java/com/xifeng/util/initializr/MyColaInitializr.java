/**
 * Project Name:MyUtil <br/>
 * File Name:MyColaInitializr.java <br/>
 * Package Name:com.xifeng.util.initializr <br/>
 * @author xiezb
 * Date:2022年10月13日下午2:36:36 <br/>
 * Copyright (c) 2022, 驼驼数科 Rights Reserved.
 */

package com.xifeng.util.initializr;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.xifeng.util.StringUtil;

import cn.hutool.core.io.FileUtil;

/**
 * ClassName: MyColaInitializr Description: Cola架构 工程自定义生成器
 * 
 * @author xiezb Date:2022年10月13日下午2:36:36 <br/>
 * @version
 * @since JDK 1.8
 */
public class MyColaInitializr {
	final static String API = "api";
	final static String INFRASTRUCTURE = "infrastructure";
	final static String DOMAIN = "domain";
	final static String CLIENT = "client";
	final static String APP = "app";
	final static String CONTROLLER = "controller";
	final static String START = "start";
	final static String MINUS = "-";

	final static String JAVA_SOURCE = "src/main/java";
	final static String CONFIG_SOURCE = "src/main/resources";
	
	final static String JAVA_TEST_SOURCE = "src/test/java";
	final static String CONFIG_TEST_SOURCE = "src/test/resources";

	final static String TEMPLATE_POM_BASE_DIR = "template/cola";

	final static String UTF8 = "UTF-8";
	final static String POM = "/pom.xml";
	final static String LOGBACK = "/logback-spring.xml";
	final static String APPLICATION = "/application-dev.properties";
	final static String APPLICATION_CLASS = "/Application.java";
	
	final static String BOOTSTRAP = "/bootstrap.properties";
	
	final static String BLANK = "/blank.txt";
	final static String DOCKERFILE = "/Dockerfile";
	final static String JENKINSFILE = "/Jenkinsfile";
	final static String SONAR = "/sonar-project.properties";
	final static String GITIGNORE = "/gitignore";

	String group;
	String artifactId;
	String basePackage;
	String projectDesc;

	String outputDir;
	boolean needApi;

	public void initializr() {
		this.generateParentPom();
		this.generateApp();
		this.generateClient();
		this.generateController();
		this.generateDomain();
		this.generateInfrastructure();
		this.generateStart();
		if(needApi) {
			this.generateApi();
		}
	}

	

	public void generateParentPom() {
		String projectName = artifactId;
		String projectPath = this.outputDir + projectName;
		FileUtil.mkdir(projectPath);
		
		String pomContent = FileUtil.readString(getProjectPomPath(null), UTF8);
		pomContent = pomContent.replace("${group}", this.group).replace("${artifactId}", this.artifactId)
				.replace("${projectDesc}", this.projectDesc).replace("${modules}", getModules());
		FileUtil.writeString(pomContent, projectPath + POM, UTF8);
		
		String dockerfile = FileUtil.readString(getProjectFilePath(null, DOCKERFILE), UTF8);
		dockerfile = dockerfile.replace("${startProjectName}", artifactId + MINUS + START).replace("${context}", this.getArtifactIdPrefix());
		FileUtil.writeString(dockerfile, projectPath + DOCKERFILE, UTF8);
		
		String jenkinsfile = FileUtil.readString(getProjectFilePath(null, JENKINSFILE), UTF8);
		jenkinsfile = jenkinsfile.replace("${artifactId}", this.artifactId);
		FileUtil.writeString(jenkinsfile, projectPath + JENKINSFILE, UTF8);
		
		String sonar = FileUtil.readString(getProjectFilePath(null, SONAR), UTF8);
		sonar = sonar.replace("${artifactId}", this.artifactId);
		FileUtil.writeString(sonar, projectPath + SONAR, UTF8);
		
		FileUtil.copy(getProjectFilePath(null, GITIGNORE), projectPath + "/.gitignore", true);
	}
	
	private String getJavaSource(String projectPath) {
		return projectPath + "/" + JAVA_SOURCE;
	}
	
	private String getConfigSource(String projectPath) {
		return projectPath + "/" + CONFIG_SOURCE;
	}
	
	private String createPackageReturnPath(String projectPath, String packagePath) {
		packagePath = packagePath.replace(".", "/");
		String javaSourcePath = this.getJavaSource(projectPath);
		String path = FileUtil.mkdir(javaSourcePath + "/" + packagePath).getAbsolutePath();
		FileUtil.copy(getProjectFilePath(null, BLANK), path + BLANK, true);
		
		return path;
	}

	public void generateStart() {
		String projectName = artifactId + MINUS + START;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		String mainClass = this.basePackage + "." + this.getApplicationName();

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("${controllerArtifactId}", this.getModuleArtifactId(CONTROLLER));
		replaceMap.put("${mainClass}", mainClass);
		this.writeModulePom(START, projectPath, replaceMap);
		
		FileUtil.mkdir(getJavaSource(projectPath));
		FileUtil.mkdir(getConfigSource(projectPath));
		FileUtil.mkdir(projectPath + "/" + JAVA_TEST_SOURCE);
		FileUtil.mkdir(projectPath + "/" + CONFIG_TEST_SOURCE);
		
		String logback = FileUtil.readString(getProjectFilePath(START, LOGBACK), UTF8);
		logback = logback.replace("${ApplicationName}", this.getApplicationName());
		FileUtil.writeString(logback, getConfigSource(projectPath) + LOGBACK, UTF8);
		
		String application = FileUtil.readString(getProjectFilePath(START, APPLICATION), UTF8);
		application = application.replace("${artifactId}", this.artifactId)
				.replace("${context}", this.getArtifactIdPrefix())
				.replace("${basePackage}", this.basePackage);
		FileUtil.writeString(application, getConfigSource(projectPath) + APPLICATION, UTF8);
		
		String bootstrap = FileUtil.readString(getProjectFilePath(START, BOOTSTRAP), UTF8);
		bootstrap = bootstrap
				.replace("${context}", this.getArtifactIdPrefix());
		FileUtil.writeString(bootstrap, getConfigSource(projectPath) + BOOTSTRAP, UTF8);
		
		String basePackagePath = this.createPackageReturnPath(projectPath, this.basePackage);
		
		String applicationClass = FileUtil.readString(getProjectFilePath(START, APPLICATION_CLASS), UTF8);
		applicationClass = applicationClass.replace("${basePackage}", this.basePackage)
				.replace("${ApplicationName}", this.getApplicationName());
		FileUtil.writeString(applicationClass, basePackagePath + "/" + this.getApplicationName() + ".java", UTF8);
	}

	public void generateController() {
		String projectName = artifactId + MINUS + CONTROLLER;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("${appArtifactId}", this.getModuleArtifactId(APP));
		this.writeModulePom(CONTROLLER, projectPath, replaceMap);
		
		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
		
		this.createPackageReturnPath(projectPath, this.basePackage + "." + CONTROLLER);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".job");
		
	}

	public void generateClient() {

		String projectName = artifactId + MINUS + CLIENT;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("${domainArtifactId}", this.getModuleArtifactId(DOMAIN));
		this.writeModulePom(CLIENT, projectPath, replaceMap);
		
		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".api");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".dto");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".dto.clientobject");

	}

	public void generateApp() {
		String projectName = artifactId + MINUS + APP;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("${clientArtifactId}", this.getModuleArtifactId(CLIENT));
		this.writeModulePom(APP, projectPath, replaceMap);
		
		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".command");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".domainevent");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".service");

	}

	public void generateDomain() {
		String projectName = artifactId + MINUS + DOMAIN;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		replaceMap.put("${infrastructureArtifactId}", this.getModuleArtifactId(INFRASTRUCTURE));
		this.writeModulePom(DOMAIN, projectPath, replaceMap);
		
		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".domain");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".domain.demo");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".domain.demo.entity");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".domain.demo.gateway");

	}

	public void generateInfrastructure() {
		String projectName = artifactId + MINUS + INFRASTRUCTURE;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		this.writeModulePom(INFRASTRUCTURE, projectPath, replaceMap);
		
		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
		String path = FileUtil.mkdir(projectPath + "/" + CONFIG_SOURCE).getAbsolutePath();
		FileUtil.copy(getProjectFilePath(null, BLANK), path + "/mapper" + BLANK, true);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".infra");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".infra.dataobject");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".infra.entity");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".infra.mapper");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".infra.rpc");

	}
	
	public void generateApi() {
		String projectName = artifactId + MINUS + API;
		String projectPath = this.outputDir + artifactId + "/" + projectName;
		FileUtil.mkdir(projectPath);

		Map<String, String> replaceMap = new HashMap<>();
		this.writeModulePom(API, projectPath, replaceMap);
		
//		FileUtil.mkdir(projectPath + "/" + JAVA_SOURCE);
//		String path = FileUtil.mkdir(projectPath + "/" + CONFIG_SOURCE).getAbsolutePath();
//		FileUtil.copy(getProjectFilePath(null, BLANK), path + "/mapper" + BLANK, true);
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".api");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".dto");
		
		this.createPackageReturnPath(projectPath, this.basePackage + ".dto.clientobject");

	}

	private static String getProjectFilePath(String project, String fileName) {

		String filePath = TEMPLATE_POM_BASE_DIR;
		if (project != null) {
			filePath += "/" + project;
		}
		if(fileName == null) {
			filePath += POM;
		}else {
			filePath += fileName;
		}
		
		return MyColaInitializr.class.getClassLoader().getResource(filePath).getPath();
	}
	
	private static String getProjectPomPath(String project) {
		return getProjectFilePath(project, POM);
	}

	public static void main(String[] args) {
		String path = getProjectPomPath(START);
		System.out.println(path);
		File file = new File(path);
		String fileSize = FileUtil.readableFileSize(file);
		System.out.println(fileSize);

		String outputDir = "D:/zmine/init/";

		String group = "com.kamelnet";
		String artifactId = "kamelnet-kth";
		String basePackage = "com.kamelnet.kth";
		String projectDesc = "快通汇服务";
		MyColaInitializr myColaInitializr = new MyColaInitializr();
		myColaInitializr.setOutputDir(outputDir);
		myColaInitializr.setArtifactId(artifactId);
		myColaInitializr.setBasePackage(basePackage);
		myColaInitializr.setGroup(group);
		myColaInitializr.setProjectDesc(projectDesc);
		myColaInitializr.setNeedApi(true);
		myColaInitializr.initializr();
	}
	
	private String getArtifactIdPrefix() {
		String[] arr = this.artifactId.split("-");

		return arr[arr.length - 1];
	}

	private String getApplicationName() {
		String[] arr = this.artifactId.split("-");
		String projectName = "";
		for (int i = 0; i < arr.length; i++) {
			projectName += StringUtil.firstUpperCase(arr[i]);
		}

		return projectName + "Application";
	}

	private String getModules() {
		StringBuilder modules = new StringBuilder();
		modules.append("<module>").append(artifactId + MINUS + START).append("</module>").append("\n");
		modules.append("\t\t").append("<module>").append(artifactId + MINUS + CONTROLLER).append("</module>")
				.append("\n");
		modules.append("\t\t").append("<module>").append(artifactId + MINUS + CLIENT).append("</module>").append("\n");
		modules.append("\t\t").append("<module>").append(artifactId + MINUS + APP).append("</module>").append("\n");
		modules.append("\t\t").append("<module>").append(artifactId + MINUS + DOMAIN).append("</module>").append("\n");
		modules.append("\t\t").append("<module>").append(artifactId + MINUS + INFRASTRUCTURE).append("</module>");
		if(needApi) {
			modules.append("\n").append("\t\t").append("<module>").append(artifactId + MINUS + API).append("</module>");
		}
		return modules.toString();
	}

	private String getModuleProjectName(String module) {
		return this.getApplicationName() + MINUS + module;
	}

	private String getModuleArtifactId(String module) {
		return this.getArtifactIdPrefix() + MINUS + module;
	}

	private String writeModulePom(String module, String projectPath, Map<String, String> replaceMap) {
		String pomContent = FileUtil.readString(getProjectPomPath(module), UTF8);

		pomContent = pomContent.replace("${group}", this.group).replace("${parentArtifactId}", this.artifactId)
				.replace("${artifactId}", this.getModuleArtifactId(module))
				.replace("${projectName}", this.getModuleProjectName(module));

		if (replaceMap != null && !replaceMap.isEmpty()) {
			for (Entry<String, String> entry : replaceMap.entrySet()) {
				pomContent = pomContent.replace(entry.getKey(), entry.getValue());
			}
		}

		FileUtil.writeString(pomContent, projectPath + POM, UTF8);
		return pomContent;
	}

	public String getGroup() {

		return group;
	}

	public void setGroup(String group) {

		this.group = group;
	}

	public String getArtifactId() {

		return artifactId;
	}

	public void setArtifactId(String artifactId) {

		this.artifactId = artifactId;
	}

	public String getBasePackage() {

		return basePackage;
	}

	public void setBasePackage(String basePackage) {

		this.basePackage = basePackage;
	}

	public String getProjectDesc() {

		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {

		this.projectDesc = projectDesc;
	}

	public String getOutputDir() {

		return outputDir;
	}

	public void setOutputDir(String outputDir) {

		this.outputDir = outputDir;
	}



	public boolean isNeedApi() {
	
		return needApi;
	}



	public void setNeedApi(boolean needApi) {
	
		this.needApi = needApi;
	}

}
