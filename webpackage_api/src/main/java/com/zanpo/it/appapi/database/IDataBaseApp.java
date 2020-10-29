package com.zanpo.it.appapi.database;

import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/29 19:52
 */
public interface IDataBaseApp {
    List<DataSourceOutputDto> getDataSources();

    String updateDataSource(DataSourceInputDto dataSourceInputDto);

    DataSourceOutputDto createDataSource(DataSourceInputDto dataSourceInputDto);

    String deleteDataSource(DataSourceInputDto dataSourceInputDto);
}
