package com.candy.autocode.argument;

import com.candy.autocode.util.FileUtils;
import com.candy.autocode.util.JavaClassNameParser;

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
            .append("   -dao    创建dao接口\n")
            .append("   -daoi   创建dao实现类\n")
            .append("   -bean   创建JavaBean\n")
            .append("   -s      创建service接口\n")
            .append("   -si     创建service实现类\n")
            .append("   -c      创建控制器类（Resource）\n")
            .append("   -a      创建以上全部（默认） \n")
            .append("configFileName 配置文件，默认为当前目录下的auto_code.properties\n")
            .toString();
    public static List<String> commands = Arrays.asList("create");
    public static List<String> options = Arrays.asList("-dao","-daoi","-bean","-s","-si","-c","-a");

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
        }
        if(arguments.length >= 3){
            if(arguments[2].startsWith("-")){
                parseOptions(arguments[2],args);
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
        if(options.contains(option.toLowerCase())){
            args.setOptions(option.toLowerCase());
        }else{
            throw new InvalidParameterException("invalid options!");
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

    public static void main(String[] arguments) {
        ArgsParser parser = new ArgsParser();
        Args args = parser.parse(new String[]{"create","myApp","-a","auto_create_myapp.xml"});
        System.out.println("command:"+args.getCommand());
        System.out.println("targetName:"+args.getTargetName());
        System.out.println("options:"+args.getOptions());
        System.out.println("configFileName:"+args.getConfigFileName());
    }

}
