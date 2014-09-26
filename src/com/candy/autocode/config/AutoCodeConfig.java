package com.candy.autocode.config;

import com.candy.autocode.argument.Args;
import com.candy.autocode.exception.NoComponentException;
import com.candy.autocode.properties.PropertiesReader;
import com.candy.autocode.util.R;
import com.candy.autocode.util.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCodeConfig {
    private AutoCodeConfig(){}

    private Component controller = new Component();
    private Component bean = new Component();
    private Component dao = new Component();
    private Component daoImpl = new Component();
    private Component service = new Component();
    private Component serviceImpl = new Component();
    private String templateBaseDir = "";

    /**
     * 目标名称
     */
    private String targetName = "";

    private Map props = new HashMap();

    public static void main(String[] args) {
        try {
            AutoCodeConfig config = AutoCodeConfig.loadConfig("test","auto_code.properties");
            System.out.println(config.getController().getClassName());
            System.out.println(config.getBean().getClassName());
            System.out.println(config.getBean().getSavePath());
            System.out.println(config.getDao().getClassName());
            System.out.println(config.getController().getSavePath());
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        }
    }
    /**
     * get a new instance of AutoConfig from the targetName and config file
     * @param targetName
     * @param configFileName
     * @return
     * @throws InvalidPropertiesFormatException
     */
    public static AutoCodeConfig loadConfig(String targetName,String configFileName) throws InvalidPropertiesFormatException {
        AutoCodeConfig autoCodeConfig = new AutoCodeConfig();
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        if(StringUtils.isNotBlank(targetName)){
            autoCodeConfig.setTargetName(targetName);
            propertiesReader.setSystemValue("args.targetName",targetName);
        }

        propertiesReader.loadProperties(configFileName);

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue("template.basedir")))){
            throw new InvalidPropertiesFormatException("template.dir is required!");
        }
        autoCodeConfig.setTemplateBaseDir(StringUtils.valueOf(propertiesReader.getValue("template.basedir")));


        loadComponent("bean",autoCodeConfig,propertiesReader);
        loadComponent("controller",autoCodeConfig,propertiesReader);
        loadComponent("dao",autoCodeConfig,propertiesReader);
        loadComponent("daoImpl",autoCodeConfig,propertiesReader);
        loadComponent("service",autoCodeConfig,propertiesReader);
        loadComponent("serviceImpl",autoCodeConfig,propertiesReader);

        autoCodeConfig.setProps(propertiesReader.toMap());
        return autoCodeConfig;
    }

    private static void loadComponent(String componentName,AutoCodeConfig autoCodeConfig,PropertiesReader propertiesReader) throws InvalidPropertiesFormatException {
        AutoCodeConfig.Component component = autoCodeConfig.getComponent(componentName);
        if(component == null){
            throw new NoComponentException(String.format("Component %s not found!",componentName));
        }

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue(componentName+".savePath")))){
            throw new InvalidPropertiesFormatException(componentName+".savePath is required!");
        }
        autoCodeConfig.getComponent(componentName).setSavePath(StringUtils.valueOf(propertiesReader.getValue(componentName+".savePath")));

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue(componentName+".className")))){
            throw new InvalidPropertiesFormatException(componentName+".className is required!");
        }
        autoCodeConfig.getComponent(componentName).setPackageName(StringUtils.valueOf(propertiesReader.getValue(componentName+".packageName")));
        autoCodeConfig.getComponent(componentName).setClassName(StringUtils.valueOf(propertiesReader.getValue(componentName+".className")));

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue(componentName+".template")))){
            autoCodeConfig.getComponent(componentName).setTemplate(autoCodeConfig.getTargetName()+ R.template.suffix);
        }else{
            autoCodeConfig.getComponent(componentName).setTemplate(StringUtils.valueOf(propertiesReader.getValue(componentName+".template")));
        }
    }

    public static AutoCodeConfig loadConfig(Args args) throws InvalidPropertiesFormatException {
        return loadConfig(args.getTargetName(),args.getConfigFileName());
    }
    public class Component{
        private String template;
        private String className;
        private String packageName;
        private String savePath;
        private String packageClassName;

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSavePath() {
            return savePath;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setSavePath(String savePath) {
            this.savePath = savePath;
        }

        public String getPackageClassName() {
            return packageClassName;
        }

        public void setPackageClassName(String packageClassName) {
            this.packageClassName = packageClassName;
        }
    }

    public Component getDao() {
        return dao;
    }
    public Component getComponent(String componentName){
        if(StringUtils.isBlank(componentName)){
            return null;
        }

//        ("-dao","-daoi","-bean","-s","-si","-c","-a");


        if("dao".equalsIgnoreCase(componentName)){
            return dao;
        }else if("daoImpl".equalsIgnoreCase(componentName) || "daoi".equalsIgnoreCase(componentName)){
            return daoImpl;
        }else if("service".equalsIgnoreCase(componentName) || "s".equalsIgnoreCase(componentName)){
            return service;
        }else if("serviceImpl".equalsIgnoreCase(componentName) || "si".equalsIgnoreCase(componentName)){
            return serviceImpl;
        }else if("bean".equalsIgnoreCase(componentName)){
            return bean;
        }else if("controller".equalsIgnoreCase(componentName) || "c".equalsIgnoreCase(componentName)){
            return controller;
        }else{
            return null;
        }
    }

    public Component getDaoImpl() {
        return daoImpl;
    }

    public Component getService() {
        return service;
    }

    public Component getServiceImpl() {
        return serviceImpl;
    }


    public Component getController() {
        return controller;
    }


    public Map getProps() {
        return props;
    }

    public void setProps(Map props) {
        this.props = props;
    }

    public String getTemplateBaseDir() {
        return templateBaseDir;
    }

    public void setTemplateBaseDir(String templateBaseDir) {
        this.templateBaseDir = templateBaseDir;
    }

    public Component getBean() {
        return bean;
    }

    public void setBean(Component bean) {
        this.bean = bean;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
