package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.config.AutoCodeConfig;
import com.candy.autocode.config.Component;
import com.candy.autocode.exception.ComponentNotFoundException;
import com.candy.autocode.util.JavaClassNameParser;
import com.candy.autocode.util.Log;
import com.candy.autocode.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCode {
    Log log = Log.getLog(AutoCode.class);

    public void run(Args args) throws IOException {
        if (args == null) {
            throw new InvalidParameterException("args is null!");
        }
        if (StringUtils.isBlank(args.getConfigFileName())) {
            throw new InvalidParameterException("config file was not found!");
        }
        if("create".equalsIgnoreCase(args.getCommand())){
            create(args);
        }else if("del".equalsIgnoreCase(args.getCommand()) || "delete".equalsIgnoreCase(args.getCommand())){
            del(args);
        }
    }

    private void del(Args args) throws IOException{
        //根据autoCodeConfig中的配置，调用coder生成代码
        AutoCodeConfig config = AutoCodeConfig.loadConfig(args);

        if(args.getOptions() == null || args.getOptions().size() <= 0){
            throw new RuntimeException("options is empty");
        }

        if(args.getOptions().contains("a")){
            delAll(config);
        }else {
            for(String option : args.getOptions()){
                delComponent(option, config);
            }
        }
    }

    private void delComponent(String componentName, AutoCodeConfig config) {
        Component component = config.getComponent(componentName);
        if(component == null ){
            throw new ComponentNotFoundException(String.format("Cann't found component :%s!",componentName));
        }
        delComponent(component, config);
    }
    private void delComponent(Component component, AutoCodeConfig config) {
        if(component == null ){
            throw new ComponentNotFoundException(String.format("No such component named %s was found!",component.getClassName()));
        }
        JavaClassNameParser parser = new JavaClassNameParser(component.getPackageClassName());
        File file = new File(component.getSavePath() + File.separator+parser.getPackagePath() + parser.getJavaFileName());
        if(file.exists()){
            if(file.delete()){
                log.error("Successfully delete file "+file.getAbsolutePath()+"!");
            }else{
                log.error("Failed to  delete file "+file.getAbsolutePath()+"!");
            }
        }else{
            log.error("File "+file.getAbsolutePath()+" was not found!");
        }
    }
    private void delAll(AutoCodeConfig config) {
        if(config == null){
            throw new RuntimeException("Invalid Param config");
        }
        if(config.getComponents() == null ){
            throw new ComponentNotFoundException("Can't Found any component,please check your configurations!");
        }
        for(Map.Entry<String,Component> component : config.getComponents().entrySet()){
            delComponent(component.getValue(), config);
        }
    }

    public void create(Args args) throws IOException {
        //根据autoCodeConfig中的配置，调用coder生成代码
        AutoCodeConfig config = AutoCodeConfig.loadConfig(args);

        if(args.getOptions() == null || args.getOptions().size() <= 0){
            throw new RuntimeException("options is empty");
        }

        if(args.getOptions().contains("a")){
            createAll(config);
        }else {
            for(String option : args.getOptions()){
                createComponent(option,config);
            }
        }
    }
    private void createComponent(String componentName,AutoCodeConfig config) throws IOException{
        Component component = config.getComponent(componentName);
        if(component == null ){
            throw new ComponentNotFoundException(String.format("Cann't found component :%s!",componentName));
        }
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),component.getSavePath(),component.getPackageClassName(),component.getTemplate());
    }
    private void createComponent(Component component,AutoCodeConfig config) throws IOException{
        if(component == null ){
            throw new ComponentNotFoundException(String.format("No such component named %s was found!",component.getClassName()));
        }
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),component.getSavePath(),component.getPackageClassName(),component.getTemplate());
    }

    private void createAll(AutoCodeConfig config) throws IOException {
        if(config == null){
            throw new RuntimeException("Invalid Param config");
        }
        if(config.getComponents() == null ){
            throw new ComponentNotFoundException("Can't Found any component,please check your configurations!");
        }
        for(Map.Entry<String,Component> component : config.getComponents().entrySet()){
            createComponent(component.getValue(),config);
        }
    }
}
