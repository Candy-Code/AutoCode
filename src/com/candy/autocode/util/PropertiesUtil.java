package com.candy.autocode.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesUtil {
    private static final String default_file_name = "config.properties";
    static  Properties properties = null;
    static{
        loadProperties();
    }
    private static void loadProperties(){
        properties = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(default_file_name);
        InputStream in2 = PropertiesUtil.class.getClassLoader().getResourceAsStream(default_file_name);
        try {
            if(in != null){
                properties.load(in);
                properties.load(in2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(in);
        }
    }
    public static <T> T getValue(String key){
        return (T)properties.get(key);
    }
    public static <T> T getValue(String key,T default_value){
        try{
            Object o = properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
}
