package com.zanpo.it.api.database.impl;

import com.zanpo.it.api.database.IDataBaseApi;
import com.zanpo.it.appapi.database.IDataBaseApp;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/29 19:53
 */
@Component
public class DataBaseApi implements IDataBaseApi {

    @Autowired
    private IDataBaseApp dataBaseApp;

    @Override
    public Result<List<DataSourceOutputDto>> getDataSources() {
        List<DataSourceOutputDto> dataSources = dataBaseApp.getDataSources();
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
        return Result.success(dataSource);
    }

    @Override
    public Result<String> deleteDataSource(DataSourceInputDto dataSourceInputDto) {
        String s = dataBaseApp.deleteDataSource(dataSourceInputDto);
        return Result.success(s);
    }
}
