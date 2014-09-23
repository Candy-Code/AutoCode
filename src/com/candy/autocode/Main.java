package com.candy.autocode;

import com.candy.autocode.argument.Args;
import com.candy.autocode.argument.ArgsParser;

import java.security.InvalidParameterException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class Main {

    public static void main(String arguments[]){
        try{
            Args args = new ArgsParser().parse(arguments);
            AutoCode autoCode = new AutoCode();
            autoCode.run(args);
        }catch(InvalidParameterException e){
            print("Error:"+e.getMessage());
            print("-------------------------------help-----------------------------\n"+ArgsParser.help);
            print(e.getMessage()+"\n"+ArgsParser.help);
        }
        catch(Exception e){
            e.printStackTrace();
            print(e.getMessage());
        }
    }

    private static void print(String str){
        System.out.println(str);
    }
}
