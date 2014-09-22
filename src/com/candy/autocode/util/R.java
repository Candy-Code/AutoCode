package com.candy.autocode.util;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class R {
    public static class Constants{
        public static final String DEFAULT_ENCODING = "utf-8";
        public static final String DEFAULT_LOCALE = "ZH_CN";
        public static final String default_system_file_name = "config.properties";
    }

    public static class template{
        public static final String suffix = ".ftl";
    }
    public static class regex{
        public static final String system_prop = ".*\\$\\$\\{([^}]+)\\}.*";
        public static final String user_prop = ".*\\$\\{([^}]+)\\}.*";
        public static String getSysPropRegex(String key){
            return ".*\\$\\$\\{"+key+"\\}.*";
        }
        public static String getUserPropRegex(String key){
            return ".*\\$\\{"+key+"\\}.*";
        }
    }
}
