package com.zanpo.it.app.database.impl;

import com.zanpo.it.appapi.database.IDataBaseApp;
import com.zanpo.it.config.HikariDataSourceProxy;
import com.zanpo.it.database.table.aggr.ColumnAggr;
import com.zanpo.it.database.table.aggr.TableAggr;
import com.zanpo.it.database.table.repository.ITableRepository;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.dto.table.TableOutputDto;
import com.zanpo.it.utils.CopyUtils;
import com.zanpo.it.utils.SpringUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 02:53
 */
@Component
@Slf4j
public class DataBaseApp implements IDataBaseApp {

    @Autowired
    private ITableRepository tableRepository;

    private DataSource initHikariDataSource(DataSourceInputDto dataSourceInputDto) throws Exception {
        String url = dataSourceInputDto.getUrl();
        log.info("mysql database url: {}", url);
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(dataSourceInputDto.getUser());
        hikariConfig.setPassword(dataSourceInputDto.getPassword());
        hikariConfig.addDataSourceProperty("cachePrepStmts", true); //是否自定义配置，为true时下面两个参数才生效
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMinimumIdle(300000);
        hikariConfig.setIdleTimeout(600000);
        hikariConfig.setMaxLifetime(3000);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048); //单条语句最大长度默认256，官方推荐2048
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource(hikariConfig);
            DataSource dataSource = SpringUtils.getBean(DataSource.class); // springComponent 是自己封装的spring 工具类，便于从 IOC 容器中获取Bean， 这里获取到初始化加载的DataSource Bean，
            if(dataSource instanceof HikariDataSourceProxy){ // 实际上是 HikariDataSourceProxy 类
                HikariDataSourceProxy hikariDataSourceProxy = (HikariDataSourceProxy) dataSource;
                hikariDataSourceProxy.setDataSource(hikariDataSource);
            }
        } catch (HikariPool.PoolInitializationException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Access denied")) {
                throw new Exception("database username or password not courrent");
            } else if (e.getMessage().contains("Unknown database")) {
                throw new Exception("database not exists ");
            } else {
                throw new Exception("data parameters not right ");
            }
        }
        return hikariDataSource;
    }

    @Override
    public List<DataSourceOutputDto> getDataSources() {
        return null;
    }

    @Override
    public String updateDataSource(DataSourceInputDto dataSourceInputDto) {
        return null;
    }

    @Override
    public DataSourceOutputDto createDataSource(DataSourceInputDto dataSourceInputDto) {
        try {
            DataSource dataSource = initHikariDataSource(dataSourceInputDto);
            if (dataSource == null){
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSourceOutputDto result = new DataSourceOutputDto();
        BeanUtils.copyProperties(dataSourceInputDto,result);
        return result;
    }

    @Override
    public String deleteDataSource(DataSourceInputDto dataSourceInputDto) {
        return null;
    }

    @Override
    public List<TableOutputDto> findAllTables(String schema) {
        List<TableAggr> allTables = tableRepository.findAllTables(schema);
        List<TableOutputDto> tableOutputDtos = CopyUtils.copyList(allTables, TableOutputDto.class);
        return tableOutputDtos;
    }

    @Override
    public String generateForeignKey(String schema) {
        List<TableAggr> allTables = tableRepository.findAllTables(schema);

        String result = "";

        for(TableAggr item : allTables){
            String name = item.getName();
            String primaryKey = item.getPrimaryKey().getName();
            String foreignKeyName = name + "_" + primaryKey;
            String primaryType = getPrimaryKeyType(item.getColumns(),primaryKey);

            for(TableAggr subItem : allTables){
                String tableName = subItem.getName();
                if(tableName.equals(name)){
                    continue;
                }
                List<ColumnAggr> columns = subItem.getColumns();
                for(ColumnAggr col : columns){
                    String colName = col.getName();
                    if(colName.equals(foreignKeyName)){
                        // 该表该字段是一个外键，指向外层的name表的主键
                        String type = col.getType();
                        if(type.equals(primaryType)){
                            String foreignName = String.format("fk_%s_%s_%s",tableName,name,primaryKey);
                            String sql = String.format("ALTER TABLE %s " +
                                    "ADD CONSTRAINT %s " +
                                    "FOREIGN KEY (%s) " +
                                    "REFERENCES %s(%s);%s", tableName,foreignName, colName, name, primaryKey,System.lineSeparator());
                            result += sql;
                        }
                    }
                }
            }

        }
        return result;
    }

    private String getPrimaryKeyType(List<ColumnAggr> columns, String primaryKey) {
        for(ColumnAggr item : columns){
            String name = item.getName();
            if(name.equals(primaryKey)){
                return item.getType();
            }
        }
        return "";
    }
}
