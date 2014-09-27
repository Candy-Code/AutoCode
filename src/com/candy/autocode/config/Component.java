package com.candy.autocode.config;

import com.candy.autocode.util.StringUtils;

/**
 * Created by ytj on 2014/9/27.
 */
public class Component {
    private String template;
    private String className;
    private String packageName;
    private String savePath;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPackageClassName() {
        if(StringUtils.isBlank(className)){
            return "";
        }
        return StringUtils.isBlank(packageName)?className:packageName+"."+className;
    }
}
