package com.candy.autocode.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(){}
    private static PropertiesReader  propertiesReader = new PropertiesReader();

    private static Properties user_properties = new Properties();
    private static Properties sys_properties = new Properties();
    static{
        loadProperties(R.Constants.default_system_file_name);
    }
    public static PropertiesReader loadProperties(String fileName){
        InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
        try {
            if(in != null){
                Properties properties = new Properties();
                properties.load(in);

                Enumeration<String> keysEnum = (Enumeration<String>)properties.propertyNames();
                String key = "";
                String value = "";
                while(keysEnum.hasMoreElements()){
                    key = keysEnum.nextElement();
                    if(StringUtils.isBlank(key)){
                        continue;
                    }
                    value = (String)properties.get(key);
                    if(StringUtils.isNotBlank(value)){
                        if(value.matches(R.regex.system_prop)){
                           value = replaceSysProp(value,sys_properties);
                        }else if(value.matches(R.regex.system_prop)){
                            value = replaceUserProp(value,sys_properties);
                        }
                    }
                    propertiesReader.user_properties.setProperty(key, value);
                }
                //propertiesReader.properties.load(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(in);
        }
        return propertiesReader;
    }

    private static String replaceUserProp(String value,Properties properties) {
        String key = RegexUtil.getContent(value,R.regex.user_prop,1);
        if(StringUtils.isNotBlank(key)){
            if(user_properties.contains(key)){
                return value.replaceAll(R.regex.getUserPropRegex(key),user_properties.getProperty(key));
            }
        }
        return value;
    }
    private static String replaceSysProp(String value,Properties properties) {
        String key = RegexUtil.getContent(value,R.regex.system_prop,1);
        if(StringUtils.isNotBlank(key)){
            if(sys_properties.contains(key)){
                return value.replaceAll(R.regex.getSysPropRegex(key),sys_properties.getProperty(key));
            }
            return value;
        }
        return value;
    }

    public static PropertiesReader getProperties(){
        return propertiesReader;
    }

    public static <T> T getValue(String key){
        return (T)user_properties.get(key);
    }
    public static <T> T getValue(String key,T default_value){
        try{
            Object o = user_properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
    public static <T> T getSystemValue(String key){
        return (T)user_properties.get(key);
    }
    public static void setSystemValue(String key,String value){
        sys_properties.setProperty(key,value);
    }
    public static <T> T getSystemValue(String key,T default_value){
        try{
            Object o = user_properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
}
