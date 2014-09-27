package com.candy.autocode.properties;

import com.candy.autocode.util.R;
import com.candy.autocode.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ytj on 2014/9/27.
 */
public class PropParser {
    public static List<Prop> parsePropList(String value){
        String[] props = value.substring(0,value.length()-1).substring(1).split(",");
        List valuesList = new ArrayList();
        for(String propStr : props){
            String[] vs = propStr.split(":");
            Prop prop = new Prop();
            if(vs.length >= 3){
                prop.setName(vs[0]);
                prop.setType(parseType(vs[1]));
                prop.setDefault_value(vs[2]);
            }else if(vs.length == 2){
                prop.setName(vs[0]);
                prop.setType(parseType(vs[1]));
                prop.setDefault_value(defaultValueOfType(vs[1]));
            }else if(vs.length == 1){
                prop.setName(vs[0]);
                prop.setType(R.Constants.default_prop_type);
                prop.setDefault_value(defaultValueOfType(R.Constants.default_prop_type));
            }
            valuesList.add(prop);
        }
        return valuesList;
    }
    public static Object parseDefaultValue(String type,String value){
        if("string".equals(type)){
            return StringUtils.around(value,"\"");
        }
        return value;
    }
    public static String parseType(String type){
        if(StringUtils.isBlank(type)){
            throw new InvalidParameterException("empty param type");
        }
        if("string".equalsIgnoreCase(type) || "str".equalsIgnoreCase(type)){
            return "String";
        }else if("int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type)){
            return "int";
        }else if("long".equalsIgnoreCase(type)){
            return "long";
        }else if("boolean".equalsIgnoreCase(type)){
            return "boolean";
        }else if("double".equalsIgnoreCase(type)){
            return "double";
        }else {
            return null;
        }
    }
    public static Object defaultValueOfType(String type){
        type = parseType(type);
        if(StringUtils.isBlank(type)){
            throw new InvalidParameterException("empty param type");
        }
        if("String".equals(type)){
            return "\"\"";
        }else if("int".equals(type)){
            return 0;
        }else if("long".equals(type)){
            return 0L;
        }else if("boolean".equals(type)){
            return false;
        }else if("double".equals(type)){
            return 0.0;
        }else {
            return null;
        }
    }
}
