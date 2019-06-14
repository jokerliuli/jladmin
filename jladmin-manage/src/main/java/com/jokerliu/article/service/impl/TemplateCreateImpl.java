package com.jokerliu.article.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.jokerliu.article.service.TemplateCreate;

import java.io.File;

/**
 * 生成文章的实际静态页面xxx123.html
 */
public class TemplateCreateImpl implements TemplateCreate {
    //自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
    //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
    TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));
    Template template = engine.getTemplate("templates/velocity_test.ftl");
    String result = template.render(Dict.create().set("name", "Hutool"));

    public static void main(String[] args) {
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH));

        //假设我们引入的是Beetl引擎，则：
        Template template = engine.getTemplate("article/news.html.ftl");
        //Dict本质上为Map，此处可用Map
        File file = FileUtil.touch("/Users/liuli/Documents/news.html");
        template.render(Dict.create().set("name", "Hutool"),file);
    }
}
