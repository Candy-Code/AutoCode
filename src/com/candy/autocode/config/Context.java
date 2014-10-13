package com.candy.autocode.config;


import com.candy.autocode.properties.method.IMethod;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yantingjun on 2014/10/10.
 */
public class Context {
    private Map<String,IMethod> methodMap = new HashMap<String,IMethod>();
    private Context(){}

    public static Context getInstance(){
        return new Context();
    }

    public void registerMethod(String methodName,IMethod method){
        methodMap.put(methodName,method);
    }
    public IMethod getMethod(String methodName){
        return methodMap.get(methodName);
    }
}
