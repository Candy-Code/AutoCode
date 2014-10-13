package com.candy.autocode.exception;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by yantingjun on 2014/9/29.
 */
public class MethodNotFoundException extends  RuntimeException{
    public MethodNotFoundException(String message){
        super(message);
    }
}
