package com.candy.autocode.properties.method;

import com.candy.autocode.annotation.PropertyMethod;

/**
 * Created by yantingjun on 2014/10/11.
 */
@PropertyMethod("uncap_first")
public class UncapFirstMethod implements IMethod{
    @Override
    public Object exec(Object... params) {
        return params[0].toString().substring(0,1).toLowerCase()+params[0].toString().substring(1);
    }
}
