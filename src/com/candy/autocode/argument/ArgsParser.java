package com.candy.autocode.argument;

import com.candy.autocode.util.FileUtils;
import com.candy.autocode.util.JavaClassNameParser;
import com.candy.autocode.util.StringUtils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yantingjun on 2014/9/21.
 *
 */
public class ArgsParser {
    public static String help = new StringBuilder()
            .append("autocode <command> targetName [options] [configFileName]\n")
            .append("command:  \n")
            .append("   create  创建代码\n")
            .append("targetName:  目标名称,由字母、数字、下划线、$组成，且不能以数字开头 \n")
            .append("options:  \n")
            .append("   -c1,c2  指定要创建的组件的名称,多个组件之间以逗号分隔 \n")
            .append("configFileName 配置文件，默认为当前目录下的auto_code.properties\n")
            .toString();
    public static List<String> commands = Arrays.asList("create");
//    public static List<String> options = Arrays.asList("-dao","-daoi","-bean","-s","-si","-c","-a");

    public Args parse(String [] arguments) throws InvalidParameterException{
        Args args = new Args();
        if(arguments == null || arguments.length <= 0){
            throw new InvalidParameterException("arguments is empty");
        }
        if(arguments.length < 2){
            throw new InvalidParameterException("miss required arguments");
        }
        if(commands.contains(arguments[0].toLowerCase())){
            args.setCommand(arguments[0]);
        }
        if(JavaClassNameParser.isInvalidClassName(arguments[1])){
            args.setTargetName(arguments[1]);
        }else{
            throw new InvalidParameterException(String.format("The targetName %s is not a valid class name!",arguments[1]));
        }
        if(arguments.length >= 3){
            if(arguments[2].startsWith("-")){
                parseOptions(arguments[2].substring(1),args);
            }else{
                parseConfigFileName(arguments[2],args);
            }
        }
        if(arguments.length >= 4){
            parseOptions(arguments[2],args);
            parseConfigFileName(arguments[3],args);
        }

        return args;
    }
    private Args parseOptions(String option,Args args){
        String[] options = option.split(",");
        for(String op : options){
            if(StringUtils.isNotBlank(op)){
                args.getOptions().add(op.toLowerCase());
            }
        }
        return args;
    }
    private Args parseConfigFileName(String configFileName,Args args){
        if(FileUtils.isInvalidPropertiesFileName(configFileName)){
            args.setConfigFileName(configFileName);
        }else{
            throw new InvalidParameterException("invalid config file name!");
        }
        return args;
    }
}
