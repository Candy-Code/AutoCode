package com.candy.autocode.properties.method;

import com.candy.autocode.annotation.PropertyMethod;

/**
 * Created by yantingjun on 2014/10/11.
 */
@PropertyMethod("cap_first")
public class CapFirstMethod implements IMethod{
    @Override
    public Object exec(Object... params) {
        return params[0].toString().substring(0,1).toUpperCase()+params[0].toString().substring(1);
    }
}
