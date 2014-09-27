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
            Args args = new ArgsParser().parse(arguments);
            printArgs(args);
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
        log.debug(String.format("%s %s %s %s",args.getCommand(),args.getTargetName(),args.getOptions(),args.getConfigFileName()));
    }
    private static void print(String str){
        System.out.println(str);
    }
}
