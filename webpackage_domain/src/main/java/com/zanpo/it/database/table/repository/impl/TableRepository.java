package com.zanpo.it.database.table.repository.impl;

import com.zanpo.it.dao.table.ITableDao;
import com.zanpo.it.database.table.aggr.TableAggr;
import com.zanpo.it.database.table.repository.ITableRepository;
import com.zanpo.it.entity.Table;
import com.zanpo.it.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:22
 */
@Component
public class TableRepository implements ITableRepository {

    @Autowired
    private ITableDao dao;

    public List<TableAggr> findAllTables(String schema) {
        List<Table> allTables = dao.findAllTables(schema);
        List<TableAggr> tableAggrs = CopyUtils.copyList(allTables, TableAggr.class);
        return tableAggrs;
    }
}
