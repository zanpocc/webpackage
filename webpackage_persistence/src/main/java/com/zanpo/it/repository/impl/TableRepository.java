package com.zanpo.it.repository.impl;

import com.zanpo.it.dao.table.ITableDao;
import com.zanpo.it.entity.Column;
import com.zanpo.it.entity.Table;
import com.zanpo.it.repository.table.ITableRepository;
import com.zanpo.it.repository.table.com.zanpo.it.aggr.ColumnAggr;
import com.zanpo.it.repository.table.com.zanpo.it.aggr.TableAggr;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<TableAggr> result = new ArrayList<TableAggr>();
        for(Table item:allTables){
            // 拷贝table属性
            TableAggr tableAggr = new TableAggr();
            BeanUtils.copyProperties(item,tableAggr);
            result.add(tableAggr);

            // 拷贝column属性
            List<Column> columns = item.getColumns();
            if(columns == null){
                continue;
            }

            List cols = new ArrayList();
            for(Column col : columns){
                ColumnAggr columnAggr = new ColumnAggr();
                BeanUtils.copyProperties(col,columnAggr);
                cols.add(columnAggr);
            }
            tableAggr.setColumns(cols);
        }

        return result;
    }
}
