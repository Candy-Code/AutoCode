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
}
