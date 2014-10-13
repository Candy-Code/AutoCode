package com.candy.autocode.properties.method;

import com.candy.autocode.annotation.PropertyMethod;

/**
 * Created by yantingjun on 2014/10/11.
 */
@PropertyMethod("upper_case")
public class UpperCaseMethod implements IMethod{
    @Override
    public Object exec(Object... params) {
        return params[0].toString().toUpperCase();
    }
}
