package com.candy.autocode.util;

import java.util.Map;
import java.util.Set;

/**
 * Created by yantingjun on 2014/9/24.
 */
public class Log {
    public static Log getLog(Class clazz){
        return new Log();
    }
    public void debug(String msg){
        System.out.println(msg);
    }
    public void error(String msg){
        System.err.println(msg);
    }
    public void info(String msg){
        System.out.println(msg);
    }


    public static void printMap(Map map,String prefix){
        for(Map.Entry entry : (Set<Map.Entry>)map.entrySet()){
            if(entry.getValue() instanceof  Map){
                System.out.println(prefix+"map:"+entry.getKey());
                printMap((Map)entry.getValue(),prefix+"--");
            }else{
                System.out.println(prefix+entry.getKey()+":"+entry.getValue());
            }
        }
    }
}
