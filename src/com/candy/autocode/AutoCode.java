package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.argument.ArgsParser;
import com.candy.autocode.config.AutoCodeConfig;
import com.candy.autocode.util.StringUtils;

import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCode {
    public void run(Args args) throws IOException {
        if (args == null) {
            throw new InvalidParameterException("args is null!");
        }
        if (StringUtils.isBlank(args.getConfigFileName())) {
            throw new InvalidParameterException("config file was not found!");
        }
        if(args.getCommand().equalsIgnoreCase("create")){
            create(args);
        }
    }
    public void create(Args args) throws IOException {
        //根据autoCodeConfig中的配置，调用coder生成代码
        AutoCodeConfig config = AutoCodeConfig.loadConfig(args);

        if("-a".equalsIgnoreCase(args.getOptions())){
            createAll(config);
        }else if(ArgsParser.options.contains(args.getOptions())){
            createComponent(args.getOptions().substring(1),config);
        }
    }
    private void createComponent(String componentName,AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getComponent(componentName).getSavePath(),
                config.getComponent(componentName).getClassName(),
                config.getComponent(componentName).getTemplate());
    }

    private void createAll(AutoCodeConfig config) throws IOException {
        for(String componentName : ArgsParser.options){
            createComponent(componentName.substring(1),config);
        }
    }
}
