package com.zanpo.it.repository.impl;

import com.zanpo.it.dao.table.ITableDao;
import com.zanpo.it.entity.Table;
import com.zanpo.it.repository.table.ITableRepository;
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

    public List<TableAggr> findAllTables() {
        List<Table> allTables = dao.findAllTables();
        // TODO:不支持List
        List<TableAggr> result = new ArrayList<TableAggr>();
        BeanUtils.copyProperties(allTables,result);
        return result;
    }
}
