package com.zanpo.it.repository.table.com.zanpo.it.aggr;

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
    private String type;
    private String key;
    private String comment;
    private String nullAble;
}
