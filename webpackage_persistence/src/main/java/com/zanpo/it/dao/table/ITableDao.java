package com.zanpo.it.dao.table;

import com.zanpo.it.entity.Table;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:24
 */
public interface ITableDao {

   List<Table> findAllTables(String schema);
}
