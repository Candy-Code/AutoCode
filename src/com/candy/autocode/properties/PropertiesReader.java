package com.candy.autocode.properties;

import com.candy.autocode.exception.InvalidPropertiesKey;
import com.candy.autocode.util.IOUtils;
import com.candy.autocode.util.R;
import com.candy.autocode.util.RegexUtil;
import com.candy.autocode.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(){}
    private static PropertiesReader propertiesReader = new PropertiesReader();

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
            if(in == null ){
                in = new FileInputStream(System.getProperty("user.dir")+ File.separator+fileName);
            }
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
        Map<String,Object> root = new HashMap<String,Object>();
        for(String key : sys_properties.stringPropertyNames()){
            String value = sys_properties.getProperty(key);
            if(key.contains(".")){
                parseComplexKey(key,root,parseValue(value));
            }else{
                root.put(key, parseValue(value));
            }
        }
        for(String key : user_properties.stringPropertyNames()){
            String value = user_properties.getProperty(key);
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
