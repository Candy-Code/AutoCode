package com.candy.autocode.config;

import com.candy.autocode.util.PropertiesUtil;

import java.util.InvalidPropertiesFormatException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCodeConfig {
    public Component controller = new Component();
    public Component dao = new Component();
    public Component daoImpl = new Component();
    public Component service = new Component();
    public Component serviceImpl = new Component();
    public String baseDir = "";

    public static void loadConfig(String configFileName) throws InvalidPropertiesFormatException {

        if(PropertiesUtil.getValue("template.basedir")==null){
            throw new InvalidPropertiesFormatException("template.dir is required!");
        }
    }
    public class Component{
        private String name;
        private String template;
        private String packageName;
    }

    public Component getDao() {
        return dao;
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

    public String getBaseDir() {
        return baseDir;
    }

    public Component getController() {
        return controller;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}
