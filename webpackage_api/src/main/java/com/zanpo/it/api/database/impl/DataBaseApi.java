package com.zanpo.it.api.database.impl;

import com.zanpo.it.api.database.IDataBaseApi;
import com.zanpo.it.app.database.IDataBaseApp;
import com.zanpo.it.dto.database.CodeGenInputDto;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.dto.database.TableOutputDto;
import com.zanpo.it.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/29 19:53
 */
@RestController
public class DataBaseApi implements IDataBaseApi {

    @Autowired
    private IDataBaseApp dataBaseApp;

    @Override
    public Result<List<DataSourceOutputDto>> findDataSources() {
        List<DataSourceOutputDto> dataSources = dataBaseApp.findDataSources();
        return Result.success(dataSources);
    }

    @Override
    public Result<String> updateDataSource(DataSourceInputDto dataSourceInputDto) {
        String s = dataBaseApp.updateDataSource(dataSourceInputDto);
        return Result.success(s);
    }

    @Override
    public Result<DataSourceOutputDto> createDataSource(DataSourceInputDto dataSourceInputDto) {
        DataSourceOutputDto dataSource = dataBaseApp.createDataSource(dataSourceInputDto);
        if (dataSource == null) {
            return Result.failed();
        }
        return Result.success(dataSource);
    }

    @Override
    public Result<String> deleteDataSource(DataSourceInputDto dataSourceInputDto) {
        String s = dataBaseApp.deleteDataSource(dataSourceInputDto);
        return Result.success(s);
    }

    @Override
    public Result<List<TableOutputDto>> findAllTables(String schema) {
        List<TableOutputDto> result = dataBaseApp.findAllTables(schema);
        return Result.success(result);
    }

    @Override
    public Result<String> generateForeignKey(String schema) {
        return Result.success(dataBaseApp.generateForeignKey(schema));
    }

    @Override
    public Result<String> generateCode(CodeGenInputDto codeGenInputDto) {
        String result = dataBaseApp.generateCode(codeGenInputDto);
        return Result.success(result);
    }

    @Override
    public Result<List<String>> findAllSchema() {
        List<String> result = dataBaseApp.findAllSchema();
        return Result.success(result);
    }
}
