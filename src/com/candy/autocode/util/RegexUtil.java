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
        if(matcher.matches()){
            return matcher.group(group);
        }
        return null;
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("name","jack");
        System.out.println(getContent("user$${name}fdjskfdj${name}",".*\\$\\$\\{([^}]+)\\}.*",1));
    }
}
