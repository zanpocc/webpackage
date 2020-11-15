package com.zanpo.it.model.codegen;

import lombok.Data;

import java.util.Map;

/**
 * 代码生成Model
 *
 * @author cg
 * @date 2020/11/15 18:35
 */
@Data
public abstract class AbsCodeGenModel {
    /**
     * 代码生成的路径，指向本机的一个绝对路径
     */
    protected String codeGenPath;
    /**
     * 生成的文件名，支持使用velocity的模板参数
     */
    protected String fileName;
    /**
     *  模版文件路径，绝对路径 or 类路径
     */
    protected String templateFilePath;
    /**
     * Velocity拓展参数，可在模板文件中引用
     */
    protected Map extraParams;

    public abstract boolean generate();
}
