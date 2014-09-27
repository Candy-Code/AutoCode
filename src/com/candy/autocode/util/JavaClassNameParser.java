package com.candy.autocode.util;

import java.security.InvalidParameterException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class JavaClassNameParser {
    private String packageName = "";
    private String className = "";
    public JavaClassNameParser(String className){
        if(StringUtils.isBlank(className)){
            throw new InvalidParameterException("className is empty");
        }
        if(!className.contains(".")){
            className = className;
            return;
        }

        this.packageName = className.substring(0,className.lastIndexOf("."));
        this.className = className.substring(className.lastIndexOf(".")+1);
    }
    public static boolean isInvalidClassName(String className){
        if(StringUtils.isBlank(className)){
            return false;
        }
        return className.matches("^[^\\d]+[0-9a-zA-Z_$]?$");
    }


    public String getClassName() {
        return className;
    }
    public String getJavaFileName() {
        return StringUtils.isBlank(className)?"":className+".java";
    }

    public String getPackageName() {
        return packageName;
    }
    public String getPackagePath(){
        return packageName==null?null:packageName.replaceAll("\\.","/")+"/";
    }

}
