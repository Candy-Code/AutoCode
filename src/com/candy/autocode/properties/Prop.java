package com.candy.autocode.properties;

/**
 * Created by yantingjun on 2014/9/24.
 * Java类的属性
 */
public class Prop {
    /**
     * 数据类型
     */
    private String type;
    /**
     * 属性名称
     */
    private String name;
    /**
     * 默认值
     */
    private Object default_value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDefault_value() {
        return default_value;
    }

    public void setDefault_value(Object default_value) {
        this.default_value = default_value;
    }
}
