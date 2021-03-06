package com.candy.autocode;

import com.candy.autocode.config.Config;
import com.candy.autocode.exception.TemplateException;
import com.candy.autocode.util.JavaClassNameParser;
import com.candy.autocode.util.Log;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

public class Coder {

    public Coder(String templateBaseLocation){
        init(templateBaseLocation);
    }
    private Configuration cfg;
    Log log = Log.getLog(Coder.class);

    public void init(String templateBaseLocation){
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration();
        cfg.setDefaultEncoding(Config.getEncoding());
        log.debug("encoding:" + Config.getEncoding());
        log.debug("locale:"+Config.getLocale());
        cfg.setEncoding(Locale.forLanguageTag(Config.getLocale()),Config.getEncoding());
        cfg.setOutputEncoding(Config.getEncoding());

        // 设置FreeMarker的模版文件位置
        File baseLocation = new File(templateBaseLocation);
        if(!baseLocation.exists()){
            throw new TemplateException(String.format("Template group was found in directory %s",baseLocation.getAbsolutePath()));
        }
        log.info("template base location : "+ templateBaseLocation);
        try {
            cfg.setDirectoryForTemplateLoading(baseLocation);
        } catch (IOException e) {
            log.error(e);
        }
    }


    public void create(Map data, String savePath, String className,String templateName) throws IOException {
        log.info(String.format("create class %s from template %s,save to %s",className,templateName,savePath));
        Template template = cfg.getTemplate(templateName);
        JavaClassNameParser parser = new JavaClassNameParser(className);

        String realFileName = savePath + File.separator+parser.getPackagePath() + parser.getJavaFileName();

        File file = new File(realFileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(realFileName,Config.getEncoding());
            template.process(data, out);
        } catch (Exception e) {
            log.error(e);
        }finally {
            if(out != null){
                out.close();
            }
        }
    }
}
