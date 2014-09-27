package com.candy.autocode.util;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class FileUtils {
    public static boolean isInvalidFileName(String filename){
        if(StringUtils.isBlank(filename)){
            return false;
        }
        return filename.matches("[^/\\\\<>\\*\\?\\|\"]+\\.[^/\\\\<>\\*\\?\\|\"]+");
    }
    public static boolean isInvalidXmlFileName(String xmlFilename) {
        if (StringUtils.isBlank(xmlFilename)) {
            return false;
        }
        return xmlFilename.toLowerCase().matches("[^/\\\\<>\\*\\?\\|\"]+\\.xml");
    }
    public static boolean isInvalidPropertiesFileName(String xmlFilename) {
        if (StringUtils.isBlank(xmlFilename)) {
            return false;
        }
        return xmlFilename.toLowerCase().matches("[^/\\\\<>\\*\\?\\|\"]+\\.properties");
    }
}
