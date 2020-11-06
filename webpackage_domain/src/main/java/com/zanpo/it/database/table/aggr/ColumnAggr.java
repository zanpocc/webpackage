package com.zanpo.it.database.table.aggr;

import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/31 00:10
 */
@Data
public class ColumnAggr {
    private String name;
    private String firstUppercaseName;
    private String firstLowercaseName;
    private String type;
    private String key;
    private String comment;
    private String nullAble;
    // 建表语句中的类型
    private String dataType;
    private String javaType;
}
