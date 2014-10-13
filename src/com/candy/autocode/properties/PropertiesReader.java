package com.candy.autocode.properties;

import com.candy.autocode.config.Context;
import com.candy.autocode.exception.InvalidPropertiesKey;
import com.candy.autocode.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class PropertiesReader {
    private PropertiesReader(Context context){
        this.context = context;
        try {
            loadSystemProperties();
            loadProperties(R.Constants.default_system_file_name);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
    }
    private static PropertiesReader propertiesReader = null;
    private static Context context;
    private static Log log = Log.getLog(PropertiesReader.class);

    private static OrderedProperties properties = new OrderedProperties();

    private void loadSystemProperties(){
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
        if(propertiesReader == null){
            propertiesReader = new PropertiesReader(Context.getInstance());
        }
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
                        value = replaceWithUsrProps(value, properties);
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
    public static String replaceWithUsrProps(String src,Properties props){
        Pattern pattern = Pattern.compile("\\$\\{([^\\?}]+)(\\?([^}\\?]+))?\\}");
        Matcher matcher=pattern.matcher(src);
        while(matcher.find()){
            String key = matcher.group(1);
            String methodName = matcher.group(3);

            if(props.containsKey(key)){
                if(StringUtils.isNotBlank(methodName)){
                    MethodHandler handler = new MethodHandler(context);
                    src = src.replaceAll("\\$\\{"+key+"\\?"+methodName+"\\}",handler.executeMethod(methodName,props.getProperty(key,"undefined")));
                }else{
                    src = src.replaceAll("\\$\\{"+key+"\\}",props.getProperty(key,"undefined"));
                }

            }
        }
        return src;
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

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
