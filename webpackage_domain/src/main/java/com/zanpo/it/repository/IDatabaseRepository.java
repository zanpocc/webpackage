package com.zanpo.it.repository;

import com.zanpo.it.entity.database.table.TableEntity;

import java.util.List;

/**
 * 数据库仓储接口
 *
 * @author cg
 * @date 2020/10/30 01:20
 */
public interface IDatabaseRepository {
    List<TableEntity> findAllTables(String schema);

    List<String> findAllSchema();
}
