package com.candy.autocode.exception;

/**
 * Created by yantingjun on 2014/9/25.
 */
public class ComponentNotFoundException extends  RuntimeException{
    public ComponentNotFoundException(String msg){
        super(msg);
    }
}
