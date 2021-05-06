package com.zanpo.it.app.database.impl;

import com.zanpo.it.app.database.IDataBaseApp;
import com.zanpo.it.config.HikariDataSourceProxy;
import com.zanpo.it.dto.database.CodeGenInputDto;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.dto.database.TableOutputDto;
import com.zanpo.it.entity.database.table.ColumnEntity;
import com.zanpo.it.entity.database.table.TableEntity;
import com.zanpo.it.exception.BaseException;
import com.zanpo.it.model.codegen.mybatis.MybatisCodeGenModelModel;
import com.zanpo.it.repository.IDatabaseRepository;
import com.zanpo.it.utils.CopyUtils;
import com.zanpo.it.utils.SpringUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 02:53
 */
@Component
@Slf4j
@SuppressWarnings("unchecked")
public class DataBaseApp implements IDataBaseApp {

    @Autowired
    private IDatabaseRepository databaseRepository;

    private DataSource initHikariDataSource(DataSourceInputDto dataSourceInputDto) {
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
            if (dataSource instanceof HikariDataSourceProxy) { // 实际上是 HikariDataSourceProxy 类
                HikariDataSourceProxy hikariDataSourceProxy = (HikariDataSourceProxy) dataSource;
                hikariDataSourceProxy.setDataSource(hikariDataSource);
            }
        } catch (HikariPool.PoolInitializationException e) {
            if (e.getMessage().contains("Access denied")) {
                throw new BaseException("500","数据库账户密码不正确");
            } else if (e.getMessage().contains("Unknown database")) {
                throw new BaseException("500","数据库不存在");
            } else {
                throw new BaseException("500","无法连接到数据库");
            }
        }
        return hikariDataSource;
    }

    @Override
    public List<DataSourceOutputDto> findDataSources() {
        HikariDataSourceProxy bean = (HikariDataSourceProxy) SpringUtils.getBean(DataSource.class);
        HikariDataSource dataSource = bean.getDataSource();
        if (dataSource == null) {
            return Collections.EMPTY_LIST;
        }

        DataSourceOutputDto dataSourceOutputDto = new DataSourceOutputDto();
        dataSourceOutputDto.setUrl(dataSource.getJdbcUrl());
        dataSourceOutputDto.setUser(dataSource.getUsername());
        dataSourceOutputDto.setPassword(dataSource.getPassword());
        return Collections.singletonList(dataSourceOutputDto);
    }

    @Override
    public String updateDataSource(DataSourceInputDto dataSourceInputDto) {
        return null;
    }

    @Override
    public DataSourceOutputDto createDataSource(DataSourceInputDto dataSourceInputDto) {

        DataSource dataSource = initHikariDataSource(dataSourceInputDto);
        if (dataSource == null) {
            return null;
        }

        DataSourceOutputDto result = new DataSourceOutputDto();
        BeanUtils.copyProperties(dataSourceInputDto, result);
        return result;
    }

    @Override
    public String deleteDataSource(DataSourceInputDto dataSourceInputDto) {
        return null;
    }

    @Override
    public List<TableOutputDto> findAllTables(String schema) {
        List<TableEntity> allTables = databaseRepository.findAllTables(schema);
        List<TableOutputDto> tableOutputDtos = CopyUtils.copyList(allTables, TableOutputDto.class);
        return tableOutputDtos;
    }

    @Override
    public String generateForeignKey(String schema) {
        List<TableEntity> allTables = databaseRepository.findAllTables(schema);

        String result = "";

        for (TableEntity item : allTables) {
            String name = item.getName();
            String primaryKey = item.getPrimaryKey().getName();
            String foreignKeyName = name + "_" + primaryKey;
            String primaryType = getPrimaryKeyType(item.getColumns(), primaryKey);

            for (TableEntity subItem : allTables) {
                String tableName = subItem.getName();
                if (tableName.equals(name)) {
                    continue;
                }
                List<ColumnEntity> columns = subItem.getColumns();
                for (ColumnEntity col : columns) {
                    String colName = col.getName();
                    if (colName.equals(foreignKeyName)) {
                        // 该表该字段是一个外键，指向外层的name表的主键
                        String type = col.getType();
                        if (type.equals(primaryType)) {
                            String foreignName = String.format("fk_%s_%s_%s", tableName, name, primaryKey);
                            String sql = String.format("ALTER TABLE %s " +
                                    "ADD CONSTRAINT %s " +
                                    "FOREIGN KEY (%s) " +
                                    "REFERENCES %s(%s);%s", tableName, foreignName, colName, name, primaryKey, System.lineSeparator());
                            result += sql;
                        }
                    }
                }
            }

        }
        return result;
    }

    @Override
    public String generateCode(CodeGenInputDto codeGenInputDto) {
        String savePath = codeGenInputDto.getSavePath();
        String schema = codeGenInputDto.getSchema();
        generateEntityCode(codeGenInputDto, savePath, schema);
        generateDaoCode(codeGenInputDto, savePath, schema);
        generateMapperCode(codeGenInputDto, savePath, schema);

        return "success";
    }

    @Override
    public List<String> findAllSchema() {
        List<String> result = databaseRepository.findAllSchema();
        return result;
    }

    private void generateMapperCode(CodeGenInputDto codeGenInputDto, String savePath, String schema) {
        Map extraMap = putArgumentToExtraMap(codeGenInputDto);
        extraMap.put("packageName",codeGenInputDto.getMapperPackageName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        extraMap.put("now",simpleDateFormat.format(new Date()));
        extraMap.put("user",System.getProperty("user.name"));

        MybatisCodeGenModelModel model = new MybatisCodeGenModelModel();
        model.setPackageName(codeGenInputDto.getMapperPackageName());
        model.setSchema(schema);
        model.setCodeGenPath(codeGenInputDto.getSavePath());
        model.setExtraParams(extraMap);
        model.setFileName(codeGenInputDto.getMapperName());

        String templatePath = null;
        try {
            templatePath = ResourceUtils.getURL("classpath:templates/Mapper.vm").getPath();
        } catch (FileNotFoundException e) {
            throw new BaseException("500","Mapper 模板文件未找到");
        }
        model.setTemplateFilePath(templatePath);
        model.generate();
    }

    private Map putArgumentToExtraMap(CodeGenInputDto codeGenInputDto) {
        String entityName = codeGenInputDto.getEntityName();
        String entityPackageName = codeGenInputDto.getEntityPackageName();
        String daoName = codeGenInputDto.getDaoName();
        String daoPackageName = codeGenInputDto.getDaoPackageName();
        String mapperName = codeGenInputDto.getMapperName();
        String mapperPackageName = codeGenInputDto.getMapperPackageName();

        // 参数中去掉拓展名
        entityName = entityName.substring(0,entityName.lastIndexOf("."));
        daoName = daoName.substring(0,daoName.lastIndexOf("."));
        mapperName = mapperName.substring(0,mapperName.lastIndexOf("."));


        Map extraMap = new HashMap();
        extraMap.put("entityName",entityName);
        extraMap.put("entityPackageName",entityPackageName);
        extraMap.put("daoName",daoName);
        extraMap.put("daoPackageName",daoPackageName);
        extraMap.put("mapperName",mapperName);
        extraMap.put("mapperPackageName",mapperPackageName);
        return extraMap;
    }

    private void generateDaoCode(CodeGenInputDto codeGenInputDto, String savePath, String schema) {
        Map extraMap = putArgumentToExtraMap(codeGenInputDto);
        extraMap.put("packageName",codeGenInputDto.getDaoPackageName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        extraMap.put("now",simpleDateFormat.format(new Date()));
        extraMap.put("user",System.getProperty("user.name"));

        MybatisCodeGenModelModel model = new MybatisCodeGenModelModel();
        model.setPackageName(codeGenInputDto.getDaoPackageName());
        model.setSchema(schema);
        model.setCodeGenPath(codeGenInputDto.getSavePath());
        model.setExtraParams(extraMap);
        model.setFileName(codeGenInputDto.getDaoName());

        String templatePath = null;
        try {
            templatePath = ResourceUtils.getURL("classpath:templates/Dao.vm").getPath();
        } catch (FileNotFoundException e) {
            throw new BaseException("500","Dao 模板文件未找到");
        }
        model.setTemplateFilePath(templatePath);
        model.generate();
    }

    private void generateEntityCode(CodeGenInputDto codeGenInputDto, String savePath, String schema) {
        Map extraMap = putArgumentToExtraMap(codeGenInputDto);
        extraMap.put("packageName",codeGenInputDto.getEntityPackageName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        extraMap.put("now",simpleDateFormat.format(new Date()));
        extraMap.put("user",System.getProperty("user.name"));

        MybatisCodeGenModelModel model = new MybatisCodeGenModelModel();
        model.setPackageName(codeGenInputDto.getEntityPackageName());
        model.setSchema(schema);
        model.setCodeGenPath(codeGenInputDto.getSavePath());
        model.setExtraParams(extraMap);
        model.setFileName(codeGenInputDto.getEntityName());

        String templatePath = null;
        try {
            templatePath = ResourceUtils.getURL("classpath:templates/Entity.vm").getPath();
        } catch (FileNotFoundException e) {
            throw new BaseException("500","Entity模板文件未找到");
        }
        model.setTemplateFilePath(templatePath);
        model.generate();
    }

    private String getPrimaryKeyType(List<ColumnEntity> columns, String primaryKey) {
        for (ColumnEntity item : columns) {
            String name = item.getName();
            if (name.equals(primaryKey)) {
                return item.getType();
            }
        }
        return "";
    }
}
