package com.zanpo.it.repository.impl;

import com.zanpo.it.dao.database.IDatabaseDao;
import com.zanpo.it.entity.database.table.ColumnEntity;
import com.zanpo.it.entity.database.table.TableEntity;
import com.zanpo.it.repository.IDatabaseRepository;
import com.zanpo.it.entity.Table;
import com.zanpo.it.utils.CopyUtils;
import com.zanpo.it.utils.NamedUtils;
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
public class DatabaseRepository implements IDatabaseRepository {

    private static final Map<String ,String > sql2javaTypeMapping = new HashMap<String, String>(){
        {
            put("varchar","String");
            put("int","Integer");
            put("date","java.sql.Date");
            put("timestamp","java.sql.Timestamp");
            put("datetime","java.sql.Date");
            put("mediumtext","String");
        }
    };

    @Autowired
    private IDatabaseDao dao;

    @Override
    public List<TableEntity> findAllTables(String schema) {
        List<Table> allTables = dao.findAllTables(schema);
        List<TableEntity> tableEntities = CopyUtils.copyList(allTables, TableEntity.class);
        for(TableEntity tableEntity : tableEntities){
            setTableNamedProperties(tableEntity);
            setPrimaryKeyNamedProperties(tableEntity);
            setPrimaryKeyJavaTypeProperties(tableEntity);
            List<ColumnEntity> columns = tableEntity.getColumns();
            for(ColumnEntity columnEntity : columns){
                setColumnJavaTypeProperties(columnEntity);
                setColumnNamedProperties(columnEntity);
            }
        }
        return tableEntities;
    }

    @Override
    public List<String> findAllSchema() {
        List<String> result = dao.findAllSchema();
        return result;
    }

    private void setPrimaryKeyJavaTypeProperties(TableEntity tableEntity) {
        ColumnEntity primaryKey = tableEntity.getPrimaryKey();
        primaryKey.setJavaType(sql2javaTypeMapping.get(primaryKey.getDataType().toLowerCase()));
    }

    private void setColumnJavaTypeProperties(ColumnEntity columnEntity) {
        columnEntity.setJavaType(sql2javaTypeMapping.get(columnEntity.getDataType().toLowerCase()));
    }

    private void setPrimaryKeyNamedProperties(TableEntity tableEntity) {
        ColumnEntity primaryKey = tableEntity.getPrimaryKey();
        String priName = primaryKey.getName();
        primaryKey.setFirstLowercaseName(NamedUtils.wordsFirstLowercase(priName,NamedUtils.UNDER_LINE));
        primaryKey.setFirstUppercaseName(NamedUtils.wordsFirstUppercase(priName,NamedUtils.UNDER_LINE));
    }

    private void setColumnNamedProperties(ColumnEntity columnEntity) {
        String columnName = columnEntity.getName();
        columnEntity.setFirstLowercaseName(NamedUtils.wordsFirstLowercase(columnName,NamedUtils.UNDER_LINE));
        columnEntity.setFirstUppercaseName(NamedUtils.wordsFirstUppercase(columnName,NamedUtils.UNDER_LINE));
    }

    private void setTableNamedProperties(TableEntity tableEntity) {
        String tableName = tableEntity.getName();
        tableEntity.setFirstLowercaseName(NamedUtils.wordsFirstLowercase(tableName,NamedUtils.UNDER_LINE));
        tableEntity.setFirstUppercaseName(NamedUtils.wordsFirstUppercase(tableName,NamedUtils.UNDER_LINE));
    }
}
