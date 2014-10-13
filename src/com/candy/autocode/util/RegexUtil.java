package com.candy.autocode.util;

import com.candy.autocode.properties.MethodHandler;

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
    public static boolean find(String src,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher=pattern.matcher(src);
        return matcher.find();
    }

    public static String hump2snake(String source){
        String regexStr = "[A-Z]";
        Matcher matcher = Pattern.compile(regexStr).matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, "_" + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        return sb.toString();
    }
}
