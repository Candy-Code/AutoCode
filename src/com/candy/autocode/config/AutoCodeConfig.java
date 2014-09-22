package com.candy.autocode.config;

import com.candy.autocode.argument.Args;
import com.candy.autocode.util.PropertiesReader;
import com.candy.autocode.util.R;
import com.candy.autocode.util.StringUtils;

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
            System.out.println(config.getDao().getClassName());
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
        propertiesReader.loadProperties(configFileName);
        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue("template.basedir")))){
            throw new InvalidPropertiesFormatException("template.dir is required!");
        }
        autoCodeConfig.setTemplateBaseDir(StringUtils.valueOf(propertiesReader.getValue("template.basedir")));

        if(StringUtils.isNotBlank(targetName)){
            autoCodeConfig.setTargetName(targetName);
            propertiesReader.setSystemValue("args.targetName",targetName);
        }
        loadComponent("bean",autoCodeConfig,propertiesReader);
        loadComponent("controller",autoCodeConfig,propertiesReader);
        loadComponent("dao",autoCodeConfig,propertiesReader);
        loadComponent("daoImpl",autoCodeConfig,propertiesReader);
        loadComponent("service",autoCodeConfig,propertiesReader);
        loadComponent("serviceImpl",autoCodeConfig,propertiesReader);
        return autoCodeConfig;
    }

    private static void loadComponent(String componentName,AutoCodeConfig autoCodeConfig,PropertiesReader propertiesReader) throws InvalidPropertiesFormatException {
        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue(componentName+".savePath")))){
            throw new InvalidPropertiesFormatException(componentName+".savePath is required!");
        }
        autoCodeConfig.getComponent(componentName).setSavePath(StringUtils.valueOf(propertiesReader.getValue(componentName+".savePath")));

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue(componentName+".className")))){
            throw new InvalidPropertiesFormatException(componentName+".className is required!");
        }
        autoCodeConfig.getComponent(componentName).setClassName(String.valueOf(propertiesReader.getValue(componentName+".className")));

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
        private String savePath;

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

        public void setSavePath(String savePath) {
            this.savePath = savePath;
        }
    }

    public Component getDao() {
        return dao;
    }
    public Component getComponent(String componentName){
        if(StringUtils.isBlank(componentName)){
            return null;
        }
        if("dao".equalsIgnoreCase(componentName)){
            return dao;
        }else if("daoImpl".equalsIgnoreCase(componentName)){
            return daoImpl;
        }else if("service".equalsIgnoreCase(componentName)){
            return service;
        }else if("serviceImpl".equalsIgnoreCase(componentName)){
            return serviceImpl;
        }else if("bean".equalsIgnoreCase(componentName)){
            return bean;
        }else if("controller".equalsIgnoreCase(componentName)){
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
