package com.candy.autocode.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class IOUtils {
    public static void close(Closeable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
