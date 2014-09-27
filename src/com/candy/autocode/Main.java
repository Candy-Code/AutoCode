package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.argument.ArgsParser;
import com.candy.autocode.util.Log;

import java.security.InvalidParameterException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class Main {

    static Log log = Log.getLog(Main.class);

    public static void main(String arguments[]){
        try{
            System.out.println(System.getProperty("user.dir"));
            printArgs(arguments);
            Args args = new ArgsParser().parse(arguments);
            AutoCode autoCode = new AutoCode();
            autoCode.run(args);
        }catch(InvalidParameterException e){
            print("Error:"+e.getMessage());
            print("-------------------------------help-----------------------------\n"+ArgsParser.help);
        }
        catch(Exception e){
            e.printStackTrace();
            print(e.getMessage());
        }
    }
    private static void printArgs(Args args){
        log.debug("command:"+args.getCommand());
        log.debug("targetName:"+args.getTargetName());
        log.debug(args.getOptions());
        log.debug(args.getConfigFileName());
    }
    private static void printArgs(String[] args){
        for(String arg : args){
            log.debug(arg);
        }
    }
    private static void print(String str){
        System.out.println(str);
    }
}
