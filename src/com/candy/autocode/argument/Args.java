package com.candy.autocode.argument;

/**
 * Created by yantingjun on 2014/9/21.
 */
public class Args {
    private String command = "";
    private String targetName = "";
    private String options = "-a";
    private String configFileName = "auto_code.properties";

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
