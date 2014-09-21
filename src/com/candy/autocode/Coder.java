package com.candy.autocode;

import com.candy.autocode.config.Config;
import com.candy.autocode.util.JavaClassNameParser;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Coder {

    public Coder(String templateBaseLocation){
        init(templateBaseLocation);
    }
    private Configuration cfg;

    public void init(String templateBaseLocation){
        // 初始化FreeMarker配置
        // 创建一个Configuration实例
        cfg = new Configuration();
        cfg.setDefaultEncoding(Config.getEncoding());

//        cfg.setLocale(Locale.forLanguageTag(Config.getLocale()));

        // 设置FreeMarker的模版文件位置
        File baseLocation = new File(templateBaseLocation);
        if(!baseLocation.exists()){
            baseLocation.mkdirs();
        }
        try {
            cfg.setDirectoryForTemplateLoading(baseLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void create(Map data, String savePath, String className,String templateName) throws IOException {
        Template template = cfg.getTemplate(templateName);
        JavaClassNameParser parser = new JavaClassNameParser(className);

        String realFileName = savePath + parser.getPackagePath() + parser.getJavaFileName();

        File file = new File(realFileName);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(realFileName);
            template.process(data, out);
            template.dump(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                out.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Coder coder = new Coder("D://template");

        Map root = new HashMap();
        String Module = "";
        String model_name = "User";
        String model_name_list = "Users";
        String instant = "user";
        String model_name_cn = "用户";
        String author = "张何兵";
        String link = "<a href=http://www.media999.com.cn>北京华亚美科技有限公司</a>";// 模块开发公司网地址
        Date date = new Date();

        root.put("module", Module);
        root.put("model_name", model_name);
        root.put("model_name_list", model_name_list);
        root.put("instant", instant);
        root.put("model_name_cn", model_name_cn);
        root.put("author", author);
        root.put("link", link);
        root.put("date", date);

        String savePath = "D://test//src//";
        String className = "com.media.dao.I" + model_name + "DAO";


        coder.create(root, savePath, className, "IDAO.ftl");
    }

}
