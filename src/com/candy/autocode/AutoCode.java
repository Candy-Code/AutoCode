package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.util.StringUtils;

import java.security.InvalidParameterException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class AutoCode {
    public void run(Args args) {
        if (args == null) {
            throw new InvalidParameterException("args is null!");
        }
        if (StringUtils.isBlank(args.getConfigFileName())) {
            throw new InvalidParameterException("config file was not found!");
        }
        if(args.getCommand().equalsIgnoreCase("create")){
            create(args);
        }
    }
    public void create(Args args) {

    }
}
