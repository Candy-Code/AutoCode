package com.candy.autocode.util;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具集
 * 封装正则表达式的常用操作
 * Created by yantingjun on 2014/9/17.
 */
public class RegexUtil {
    public static String getContent(String src,String reg,int group){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher=pattern.matcher(src);
        if(matcher.find()){
            return matcher.group(group);
        }
        return null;
    }

    public static String replayWithSysProps(String src,Properties props){
        Pattern pattern = Pattern.compile("#\\{([^}]+)\\}");
        Matcher matcher=pattern.matcher(src);
        while(matcher.find()){
            String key = matcher.group(1);
            if(props.containsKey(key)){
                src = src.replaceAll("#\\{"+key+"\\}",props.getProperty(key,"undefined"));
            }
        }
        return src;
    }
    public static String replayWithUsrProps(String src,Properties props){
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)\\}");
        Matcher matcher=pattern.matcher(src);
        while(matcher.find()){
            String key = matcher.group(1);
            if(props.containsKey(key)){
                src = src.replaceAll("\\$\\{"+key+"\\}",props.getProperty(key,"undefined"));
            }
        }
        return src;
    }
}
