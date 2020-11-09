package com.zanpo.it.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

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
        // 资源加载方式：1、class 2、file 3、jar
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        // 资源加载类，与资源加载方式对应:1、ClasspathResourceLoader 2、FileResourceLoader 3、JarResourceLoader
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "/");
        ve.setProperty("classpath.resource.loader.class", FileResourceLoader.class.getName());
        ve.setProperty("input.encoding", "UTF-8");
        ve.setProperty("output.encoding", "UTF-8");
        ve.init();
    }

    public static final void merge(VelocityContext ctx, String sourceFile, Writer writer) throws IOException {
        // 创建模板
        Template t = ve.getTemplate(sourceFile);
        // 写出文件
        t.merge(ctx, writer);
        // 从缓冲区刷新到本地
        writer.flush();
    }


    public static final void merge(VelocityContext ctx, String sourceFile, String target) throws IOException {
        merge(ctx, sourceFile, new FileWriter(target));
    }

    public static final void merge(VelocityContext ctx, String sourceFile, String sourceEncoding, Writer writer) {
        // 创建模板
        Template t = ve.getTemplate(sourceFile);
        // 写出文件
        t.merge(ctx, writer);
    }

    public static final void merge(VelocityContext ctx, String sourceFile, String sourceEncoding, String target) throws IOException {
        merge(ctx, sourceFile, sourceEncoding, new FileWriter(target));
    }

    /**
     * 仅支持简单的$变量替换，不支持集合等
     *
     * @param ctx    上下文
     * @param source 源字符串
     * @return 替换后的字符串
     */
    public static final String replace(VelocityContext ctx, String source) {
        String result = source;
        Object[] keys = ctx.getKeys();
        for (Object key : keys) {
            result = result.replace("$" + key, ctx.get(key + "") + "");
            result = result.replace("${" + key + "}", ctx.get(key + "") + "");

        }
        return result;
    }

}
