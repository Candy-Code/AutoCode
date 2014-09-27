package com.candy.autocode.util;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class StringUtils {
    public static boolean isBlank(String str){
        return str==null||str.trim().length()<=0;
    }
    public static boolean isNotBlank(String str){
        return !isBlank(str);
    }
    public static String valueOf(Object value){
        return value==null?"":value.toString();
    }

    public static String around(String value, String aroundStr) {
        return aroundStr+value+aroundStr;
    }
}
