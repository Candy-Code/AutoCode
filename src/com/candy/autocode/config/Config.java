package com.candy.autocode.config;

import com.candy.autocode.util.PropertiesUtil;

import java.util.Locale;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class Config {
    private static final String DEFAULT_ENCODING = "utf-8";
    private static final String DEFAULT_LOCALE = "ZH_CN";

    private static String encoding = DEFAULT_ENCODING;

    public static String getEncoding() {
        return encoding;
    }

    public static void setEncoding(String encoding) {
        Config.encoding = encoding;
    }

    public static String getLocale() {
        return locale;
    }

    public static void setLocale(String locale) {
        Config.locale = locale;
    }

    private static String locale = DEFAULT_ENCODING;
    static{
        loadConfig();
    }
    private static void loadConfig(){
        encoding = PropertiesUtil.getValue("encoding",DEFAULT_ENCODING);
        locale = PropertiesUtil.getValue("locale",DEFAULT_LOCALE);
    }
}
