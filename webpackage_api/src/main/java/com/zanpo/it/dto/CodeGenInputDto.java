package com.zanpo.it.dto;

import lombok.Data;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/12 21:42
 */
@Data
public class CodeGenInputDto {
    private String savePath;
    private String schema;
    private String entityName;
    private String daoName;
    private String mapperName;
    private String entityPackageName;
    private String daoPackageName;
    private String mapperPackageName;
    private List<String> tables;
}
