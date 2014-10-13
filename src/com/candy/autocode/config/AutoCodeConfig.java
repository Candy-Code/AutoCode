package com.candy.autocode.config;

import com.candy.autocode.argument.Args;
import com.candy.autocode.freemarker.method.Hump2snakeMethod;
import com.candy.autocode.properties.PropertiesReader;
import com.candy.autocode.util.R;
import com.candy.autocode.util.StringUtils;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * 用于存储用户配置的关于自动生成代码的信息
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCodeConfig {
    private AutoCodeConfig(){}
    private Map<String,Component> components = new HashMap<String,Component>();
    private String templateBaseDir = "";

    /**
     * 目标名称
     */
    private String targetName = "";

    private Map props = new HashMap();

    /**
     * get a new instance of AutoConfig from the targetName and config file
     * @param autoCodeConfig
     * @param variables
     * @return
     * @throws InvalidPropertiesFormatException
     */
//    public static AutoCodeConfig loadConfig(String targetName,String configFileName) throws InvalidPropertiesFormatException, FileNotFoundException {
//
//    }
    private static void loadComponents(AutoCodeConfig autoCodeConfig,Map variables) throws InvalidPropertiesFormatException {
        if(variables == null || variables.get("component") == null){
            throw new InvalidPropertiesFormatException("Can't find any component!");
        }
        if(!(variables.get("component") instanceof Map)){
            throw new InvalidPropertiesFormatException("Component config error!");
        }
        Map componentMap = (Map)variables.get("component");
        for(Map.Entry<String,Map> entry : (Set<Map.Entry<String,Map>>)componentMap.entrySet()){
            if(entry.getValue()!= null &&(entry.getValue() instanceof  Map)){
                autoCodeConfig.addComponent(entry.getKey(),buildComponent(entry.getKey(),entry.getValue()));
                //把component加入根结构中，方便模板调用
                variables.put(entry.getKey(),entry.getValue());
            }
        }
    }
    private static Component buildComponent(String componentName,Map componentMap)
            throws InvalidPropertiesFormatException{
        if(componentMap == null || componentMap.size() <= 0){
            throw new InvalidPropertiesFormatException("Component props is empty!");
        }
        if(StringUtils.isBlank(componentName)){
            throw new InvalidPropertiesFormatException("componentName is empty!");
        }
        Component component = new Component();
        if(componentMap.get("className") == null || !(componentMap.get("className") instanceof String)){
            throw new InvalidPropertiesFormatException("The className of component "+componentName+" is empty!");
        }
        component.setClassName(StringUtils.valueOf(componentMap.get("className")));

        if(componentMap.get("savePath") == null || !(componentMap.get("savePath") instanceof String)){
            throw new InvalidPropertiesFormatException("The savePath of component "+componentName+" is empty!");
        }
        component.setSavePath(StringUtils.valueOf(componentMap.get("savePath")));
        //报名允许为空
        component.setPackageName(StringUtils.valueOf(componentMap.get("packageName")));

        //模板为空，则根据组件名称匹配模板
        if(componentMap.get("template") == null || !(componentMap.get("template") instanceof String)){
            component.setTemplate(componentName+ R.template.suffix);
        }else if(StringUtils.valueOf(componentMap.get("template")).endsWith(R.template.suffix)){
            component.setTemplate(StringUtils.valueOf(componentMap.get("template")));
        }else{
            component.setTemplate(componentMap.get("template")+ R.template.suffix);
        }
        return component;
    }

    public static AutoCodeConfig loadConfig(Args args) throws InvalidPropertiesFormatException, FileNotFoundException {
        AutoCodeConfig autoCodeConfig = new AutoCodeConfig();
        PropertiesReader propertiesReader = PropertiesReader.getInstance();

        autoCodeConfig.setTargetName(args.getTargetName());
        propertiesReader.setValue("args.targetName",args.getTargetName());
        propertiesReader.setValue("args.templateGroup",args.getTemplateGroup());
        propertiesReader.setValue("args.command",args.getCommand());
        propertiesReader.setValue("args.options",args.getOptions().toString());

        propertiesReader.loadProperties(args.getConfigFileName());

        if(StringUtils.isBlank(StringUtils.valueOf(propertiesReader.getValue("template.basedir")))){
            throw new InvalidPropertiesFormatException("template.dir is required!");
        }
        autoCodeConfig.setTemplateBaseDir(StringUtils.valueOf(propertiesReader.getValue("template.basedir")));

        Map variables = propertiesReader.toMap();
        loadComponents(autoCodeConfig,variables);
        loadExtraVariables(variables);
        loadExtraMethod(variables);

        autoCodeConfig.setProps(variables);
        return autoCodeConfig;
    }
    private static void loadExtraVariables(Map variables){
        variables.put("sysdate",new Date());
    }
    private static void loadExtraMethod(Map variables){
        variables.put("hump2snake",new Hump2snakeMethod());
    }


    public Component getComponent(String componentName){
        return components.get(componentName);
    }
    public Component addComponent(String componentName,Component component){
        if(StringUtils.isBlank(componentName)){
            throw new InvalidParameterException("componentName is empty");
        }
        return components.put(componentName.toLowerCase(),component);
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

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Map<String, Component> getComponents() {
        return components;
    }
}
