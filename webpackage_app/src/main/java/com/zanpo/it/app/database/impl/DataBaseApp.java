package com.zanpo.it.app.database.impl;

import com.zanpo.it.SpringUtils;
import com.zanpo.it.appapi.database.IDataBaseApp;
import com.zanpo.it.config.HikariDataSourceProxy;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.repository.table.ITableRepository;
import com.zanpo.it.repository.table.com.zanpo.it.aggr.TableAggr;
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
    ITableRepository tableRepository;

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
        List<TableAggr> allTables = tableRepository.findAllTables();
        log.info("{}",allTables);
        return null;
    }

    @Override
    public String updateDataSource(DataSourceInputDto dataSourceInputDto) {
        return null;
    }

    @Override
    public DataSourceOutputDto createDataSource(DataSourceInputDto dataSourceInputDto) {
        // TODO:
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
}
