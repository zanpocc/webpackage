package com.zanpo.it.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 21:39
 */
public final class VelocityUtils {

    private static final VelocityEngine ve = new VelocityEngine();

    static {
        // 创建引擎，初始化
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
    }

    public static final void merge(VelocityContext ctx, String source, Writer writer) throws IOException {
        // 创建模板
        Template t = ve.getTemplate(source);
        // 写出文件
        t.merge(ctx, writer);
    }

    public static final void merge(VelocityContext ctx, String source, String target) throws IOException {
        merge(ctx, source, new FileWriter(target));
    }

    public static final void merge(VelocityContext ctx, String source, String sourceEncoding, Writer writer) throws IOException {
        // 创建模板
        Template t = ve.getTemplate(source);
        // 写出文件
        t.merge(ctx, writer);
    }

    public static final void merge(VelocityContext ctx, String source, String sourceEncoding, String target) throws IOException {
        merge(ctx, source, sourceEncoding, new FileWriter(target));
    }

}
