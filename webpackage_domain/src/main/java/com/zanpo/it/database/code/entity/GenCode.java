package com.zanpo.it.database.code.entity;

import lombok.Data;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/4 22:24
 */
@Data
public class GenCode {
    // 包路径名
    private String packageName;
    // 模板文件
    private List<FileTemplate> fileTemplates;
}
