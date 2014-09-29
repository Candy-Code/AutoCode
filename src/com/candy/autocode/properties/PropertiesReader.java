package com.candy.autocode.properties;

import com.candy.autocode.exception.InvalidPropertiesKey;
import com.candy.autocode.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(){}
    private static PropertiesReader propertiesReader = new PropertiesReader();

    private static Log log = Log.getLog(PropertiesReader.class);

    private static OrderedProperties properties = new OrderedProperties();
//    private static OrderedProperties sys_properties = new OrderedProperties();
    static{
        try {
            loadSystemProperties();
            propertiesReader.loadProperties(R.Constants.default_system_file_name);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
    }
    private static void loadSystemProperties(){
        properties.put("user.dir", System.getProperty("user.dir"));
        properties.put("user.country", System.getProperty("user.country"));
        properties.put("user.home", System.getProperty("user.home"));
        properties.put("user.name", System.getProperty("user.name"));
        properties.put("user.language", System.getProperty("user.language"));
        properties.put("os.name", System.getProperty("os.name"));
        properties.put("file.encoding", System.getProperty("file.encoding"));
        properties.put("file.separator", System.getProperty("file.separator"));
    }
    public static PropertiesReader getInstance(){
        return propertiesReader;
    }
    public void loadProperties(String fileName) throws FileNotFoundException {
        InputStream in = null;
        try {
            in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
            if(in == null ){
                in = new FileInputStream(System.getProperty("user.dir")+ File.separator+fileName);
            }
            if(in != null){
                properties.load(in);

                String value = "";
                for(String key : properties.stringPropertyNames()){
                    value = "";
                    if(StringUtils.isBlank(key)){
                        continue;
                    }
                    value = (String) properties.get(key);
                    if(StringUtils.isNotBlank(value)){
//                        value = RegexUtil.replayWithSysProps(value, sys_properties);
                        value = RegexUtil.replayWithUsrProps(value, properties);
                    }
                    properties.setProperty(key, value);
                }
            }
        }catch (FileNotFoundException e){
            throw e;
        }
        catch (Exception e) {
            log.error(e);
        }finally {
            IOUtils.close(in);
        }
    }

    public static PropertiesReader getProperties(){
        return propertiesReader;
    }
    public Map toMap(){
        Map<String,Object> root = new HashMap<String,Object>();
//        for(String key : sys_properties.stringPropertyNames()){
//            String value = sys_properties.getProperty(key);
//            if(key.contains(".")){
//                parseComplexKey(key,root,parseValue(value));
//            }else{
//                root.put(key, parseValue(value));
//            }
//        }
        for(String key : properties.stringPropertyNames()){
            String value = properties.getProperty(key);
            if(key.contains(".")){
                parseComplexKey(key,root,parseValue(value));
            }else{
                root.put(key, parseValue(value));
            }
        }
        return root;
    }

    private static void parseComplexKey(String key,Map node,Object value){
        if(StringUtils.isBlank(key)){
            return;
        }
        if(!key.contains(".")){
            node.put(key, value);
            return;
        }
        if(key.startsWith(".") || key.endsWith(".")){
            throw new InvalidPropertiesKey("Invalid properties key:"+key);
        }
        String name = key.substring(0,key.indexOf("."));
        Object map = node.get(name);
        if(map!=null && map instanceof  Map){
            parseComplexKey(key.substring(key.indexOf(".")+1),(Map)map,value);
        }else{
            Map new_map = new HashMap();
            node.put(name,new_map);
            parseComplexKey(key.substring(key.indexOf(".")+1),new_map,value);
        }
    }
    private static Object parseValue(String value){
        if(value.startsWith("[")&&value.endsWith("]")){
            return PropParser.parsePropList(value);
        }else{
           return value;
        }
    }


    public <T> T getValue(String key){
        return (T) properties.get(key);
    }
    public <T> T getValue(String key,T default_value){
        try{
            Object o = properties.get(key);
            return o == null?default_value:(T)o;
        }catch(Exception e){
            return default_value;
        }
    }
    public void setValue(String key,Object value){
        properties.put(key,value);
    }
//    public <T> T getSystemValue(String key){
//        return (T) properties.get(key);
//    }
//    public void setSystemValue(String key,String value){
//        sys_properties.setProperty(key,value);
//    }
//    public <T> T getSystemValue(String key,T default_value){
//        try{
//            Object o = properties.get(key);
//            return o == null?default_value:(T)o;
//        }catch(Exception e){
//            return default_value;
//        }
//    }
}
