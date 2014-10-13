package com.candy.autocode.properties;

import com.candy.autocode.annotation.PropertyMethod;
import com.candy.autocode.config.Context;
import com.candy.autocode.exception.MethodNotFoundException;
import com.candy.autocode.properties.method.IMethod;
import com.candy.autocode.util.ClassScanner;
import com.candy.autocode.util.R;
import com.candy.autocode.util.RegexUtil;
import com.candy.autocode.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created by yantingjun on 2014/10/10.
 */
public class MethodHandler {
    private Context context;
    public static boolean findMethod(String value){
        return RegexUtil.find(value, R.regex.method);
    }
    public MethodHandler(Context context){
        this.context = context;
        autoLoadMethod();
    }
    public String executeMethod(IMethod method,String value){
        return (String)method.exec(value);
    }
    public String executeMethod(String methodName,String value){
        if(context.getMethod(methodName)!=null){
            return (String)context.getMethod(methodName).exec(value);
        }
        return null;
    }

    private void autoLoadMethod(){
        Set<Class<?>> classes = ClassScanner.getClasses("com.candy.autocode.properties.method");
        for(Class clazz : classes){
            if(clazz.isAnnotationPresent(PropertyMethod.class)){
                PropertyMethod propertyMethodAnnotation = (PropertyMethod)clazz.getAnnotation(PropertyMethod.class);
                try {
                    context.registerMethod(propertyMethodAnnotation.value(),(IMethod)clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
