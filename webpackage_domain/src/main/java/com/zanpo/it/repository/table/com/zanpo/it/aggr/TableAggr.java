package com.zanpo.it.repository.table.com.zanpo.it.aggr;

import lombok.Data;

import java.util.List;


/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/31 00:10
 */
@Data
public class TableAggr {
    private String name;
    private String comment;
    private List<ColumnAggr> columns;
}
