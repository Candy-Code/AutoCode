package com.candy.autocode.freemarker.method;

import com.candy.autocode.util.RegexUtil;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by ytj on 2014/9/27.
 */
public class TableNameMethod implements TemplateMethodModel {
    @Override
    public Object exec(List params) throws TemplateModelException {
        if(params == null || params.size() < 1){
            throw new InvalidParameterException("invalid prams");
        }
        return RegexUtil.hump2snake(params.get(0)+"");
    }
}
