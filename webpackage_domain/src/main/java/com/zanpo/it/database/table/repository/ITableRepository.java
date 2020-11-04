package com.zanpo.it.database.table.repository;

import com.zanpo.it.database.table.com.zanpo.it.aggr.TableAggr;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:20
 */
public interface ITableRepository {
    List<TableAggr> findAllTables(String schema);
}
