package com.zanpo.it.dto.database;

import lombok.Data;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/1 21:15
 */
@Data
public class TableOutputDto {
    private String name;
    private String firstLowercaseName;
    private String firstUppercaseName;
    private String comment;
    private ColumnOutputDto primaryKey;
    private List<ColumnOutputDto> columns;
}
