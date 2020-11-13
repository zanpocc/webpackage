package com.zanpo.it.appapi.database;

import com.zanpo.it.dto.CodeGenInputDto;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.dto.database.TableOutputDto;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/29 19:52
 */
public interface IDataBaseApp {
    List<DataSourceOutputDto> findDataSources();

    String updateDataSource(DataSourceInputDto dataSourceInputDto);

    DataSourceOutputDto createDataSource(DataSourceInputDto dataSourceInputDto);

    String deleteDataSource(DataSourceInputDto dataSourceInputDto);

    List<TableOutputDto> findAllTables(String schema);

    String generateForeignKey(String schema);

    String generateCode(CodeGenInputDto codeGenInputDto);

    List<String> findAllSchema();
}
