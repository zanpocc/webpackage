package com.zanpo.it.database.code.entity;

import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 23:04
 */
@Data
public class FileTemplate {
    // 文件名，带后缀
    private String file;
    // 绝对路径
    private String template;

    public FileTemplate(String file, String template) {
        this.file = file;
        this.template = template;
    }
}
