package com.candy.autocode.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(){}
    private static PropertiesReader  propertiesReader = new PropertiesReader();

    private static OrderedProperties user_properties = new OrderedProperties();
    private static OrderedProperties sys_properties = new OrderedProperties();
    static{
        loadProperties(R.Constants.default_system_file_name);
    }
    public static PropertiesReader getInstance(){
        return propertiesReader;
    }
    public static void loadProperties(String fileName){
        InputStream in = null;
        try {
            in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
            if(in != null){
//                Properties properties = new Properties();
//                properties.load(in);
                user_properties.load(in);

                String value = "";
                for(String key : user_properties.stringPropertyNames()){
                    value = "";
                    if(StringUtils.isBlank(key)){
                        continue;
                    }
                    value = (String)user_properties.get(key);
                    if(StringUtils.isNotBlank(value)){
                        if(value.matches(R.regex.system_prop)){
                           value = replaceSysProp(value,sys_properties);
                        }else if(value.matches(R.regex.user_prop)){
                            value = replaceUserProp(value,user_properties);
                        }
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


    private static String replaceUserProp(String value,Properties properties) {
        String key = null;
        while(StringUtils.isNotBlank(key = RegexUtil.getContent(value,R.regex.user_prop,1))){
            if(StringUtils.isNotBlank(key)){
                if(user_properties.contains(key)){
                    value = value.replaceAll(R.regex.getUserPropRegex(key),user_properties.getProperty(key));
                }
            }
        }

        return value;
    }
    public static void main(String[] args) {
//        Properties properties = new Properties();
//        properties.setProperty("name","jack");
//        System.out.println(replaceSysProp("user$${name}fdjskfdj${name}",properties));
        System.out.println("user$${name}fdjskfdj${name}".replaceAll("\\$\\$\\{name\\}","jack"));
    }

    private static String replaceSysProp(String value,Properties properties) {
        String key = null;
        while(StringUtils.isNotBlank(key = RegexUtil.getContent(value,R.regex.system_prop,1))){
            if(StringUtils.isNotBlank(key)){
                if(sys_properties.contains(key)){
                    value = value.replaceAll(R.regex.getUserPropRegex(key),sys_properties.getProperty(key));
                }
            }
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
