package com.zanpo.it.dao.database;

import com.zanpo.it.entity.Table;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:24
 */
public interface IDatabaseDao {

   List<Table> findAllTables(String schema);

   List<String> findAllSchema();

}
