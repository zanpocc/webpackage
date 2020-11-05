package com.zanpo.it.database.table.repository.impl;

import com.zanpo.it.dao.table.ITableDao;
import com.zanpo.it.database.table.aggr.ColumnAggr;
import com.zanpo.it.database.table.aggr.TableAggr;
import com.zanpo.it.database.table.repository.ITableRepository;
import com.zanpo.it.entity.Table;
import com.zanpo.it.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:22
 */
@Component
public class TableRepository implements ITableRepository {

    private static final Map<String ,String > sql2javaTypeMapping = new HashMap<String, String>(){
        {
            put("varchar","String");
            put("int","int");
            put("date","java.sql.Date");
            put("timestamp","java.sql.Timestamp");
            put("mediumtext","String");
        }
    };


    @Autowired
    private ITableDao dao;

    public List<TableAggr> findAllTables(String schema) {
        List<Table> allTables = dao.findAllTables(schema);
        List<TableAggr> tableAggrs = CopyUtils.copyList(allTables, TableAggr.class);
        for(TableAggr tableAggr : tableAggrs){
            List<ColumnAggr> columns = tableAggr.getColumns();
            for(ColumnAggr columnAggr : columns){
                columnAggr.setJavaType(sql2javaTypeMapping.get(columnAggr.getDataType().toLowerCase()));
            }
        }
        return tableAggrs;
    }
}
