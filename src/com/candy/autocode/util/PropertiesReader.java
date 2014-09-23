package com.candy.autocode.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(){}
    private static PropertiesReader  propertiesReader = new PropertiesReader();

    private static OrderedProperties user_properties = new OrderedProperties();
    private static OrderedProperties sys_properties = new OrderedProperties();
    static{
        propertiesReader.loadProperties(R.Constants.default_system_file_name);
    }
    public static PropertiesReader getInstance(){
        return propertiesReader;
    }
    public void loadProperties(String fileName){
        InputStream in = null;
        try {
            in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
            if(in != null){
                user_properties.load(in);

                String value = "";
                for(String key : user_properties.stringPropertyNames()){
                    value = "";
                    if(StringUtils.isBlank(key)){
                        continue;
                    }
                    value = (String)user_properties.get(key);
                    if(StringUtils.isNotBlank(value)){
                        value = RegexUtil.replayWithSysProps(value, sys_properties);
                        value = RegexUtil.replayWithUsrProps(value, user_properties);
                    }
                    user_properties.setProperty(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(in);
        }
    }

    public static PropertiesReader getProperties(){
        return propertiesReader;
    }
    public Map toMap(){
        Map<String,Object> map = new HashMap<String,Object>();
        for(String key : sys_properties.stringPropertyNames()){
            map.put(key,sys_properties.getProperty(key));
        }
        for(String key : user_properties.stringPropertyNames()){
            String value = user_properties.getProperty(key);
            if(value.startsWith("[")&&value.endsWith("]")){
                map.put(key, Arrays.asList(value.substring(0,value.length()-1).substring(1).split(",")));
            }else{
                map.put(key,value);
            }
        }
        return map;
    }
    public <T> T getValue(String key){
        return (T)user_properties.get(key);
    }
    public <T> T getValue(String key,T default_value){
        try{
            Object o = user_properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
    public <T> T getSystemValue(String key){
        return (T)user_properties.get(key);
    }
    public void setSystemValue(String key,String value){
        sys_properties.setProperty(key,value);
    }
    public <T> T getSystemValue(String key,T default_value){
        try{
            Object o = user_properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
}
