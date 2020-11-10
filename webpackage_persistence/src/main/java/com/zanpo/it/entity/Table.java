package com.zanpo.it.entity;

import lombok.Data;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 23:57
 */
@Data
public class Table {
    private String name;
    private String comment;
    private Column primaryKey;
    private List<Column> columns;
}
