package com.zanpo.it.entity.database.table;

import lombok.Data;

import java.util.List;


/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/31 00:10
 */
@Data
public class TableEntity {
    private String name;
    private String firstLowercaseName;
    private String firstUppercaseName;
    private String comment;
    private ColumnEntity primaryKey;
    private List<ColumnEntity> columns;
}
