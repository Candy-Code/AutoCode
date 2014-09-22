package com.candy.autocode.config;

import com.candy.autocode.util.PropertiesReader;
import com.candy.autocode.util.R;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class Config {

    private static String encoding = R.Constants.DEFAULT_ENCODING;

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

    private static String locale = R.Constants.DEFAULT_LOCALE;
    static{
        loadConfig();
    }
    private static void loadConfig(){
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        propertiesReader.loadProperties(R.Constants.default_system_file_name);
        encoding = propertiesReader.getValue("encoding",R.Constants.DEFAULT_ENCODING);
        locale = propertiesReader.getValue("locale",R.Constants.DEFAULT_LOCALE);
    }
}
