package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.config.AutoCodeConfig;
import com.candy.autocode.util.StringUtils;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.InvalidPropertiesFormatException;

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
        }else if("-dao".equalsIgnoreCase(args.getOptions())){
            createDao(config);
        }else if("-daoi".equalsIgnoreCase(args.getOptions())){
            createDaoImpl(config);
        }else if("-bean".equalsIgnoreCase(args.getOptions())){
            createBean(config);
        }else if("-s".equalsIgnoreCase(args.getOptions())){
            createService(config);
        }else if("-si".equalsIgnoreCase(args.getOptions())){
            createServiceImpl(config);
        }else if("-c".equalsIgnoreCase(args.getOptions())){
            createController(config);
        }
    }

    private void createServiceImpl(AutoCodeConfig config) throws IOException {
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getServiceImpl().getSavePath(),
                config.getServiceImpl().getClassName(),config.getServiceImpl().getTemplate());
    }

    private void createController(AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getController().getSavePath(),
                config.getController().getClassName(),config.getController().getTemplate());
    }

    private void createService(AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getController().getSavePath(),
                config.getController().getClassName(),config.getController().getTemplate());
    }

    private void createBean(AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getBean().getSavePath(),
                config.getBean().getClassName(),config.getBean().getTemplate());
    }

    private void createDaoImpl(AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getDaoImpl().getSavePath(),
                config.getDaoImpl().getClassName(),config.getDaoImpl().getTemplate());
    }

    private void createDao(AutoCodeConfig config) throws IOException{
        Coder coder = new Coder(config.getTemplateBaseDir());
        coder.create(config.getProps(),config.getDao().getSavePath(),
                config.getDao().getClassName(),config.getDao().getTemplate());
    }

    private void createAll(AutoCodeConfig config) throws IOException {
        createBean(config);
        createDao(config);
        createDaoImpl(config);
        createService(config);
        createServiceImpl(config);
        createController(config);
    }
}
