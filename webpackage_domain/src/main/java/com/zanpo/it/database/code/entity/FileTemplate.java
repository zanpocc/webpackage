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
    // 大包名下的小目录
    private String dirs;
    // 文件名，带后缀
    private String file;
    // 绝对路径
    private String template;

    public FileTemplate(String dirs, String file, String template) {
        this.dirs = dirs;
        this.file = file;
        this.template = template;
    }
}
