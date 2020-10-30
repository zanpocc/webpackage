package com.zanpo.it.api.database;

import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/29 19:51
 */
@Api("数据库设置API")
@RequestMapping("/database")
@RestController
public interface IDataBaseApi {

    @ApiOperation("列举所以数据源信息")
    @GetMapping("/datasource/list")
    Result<List<DataSourceOutputDto>> getDataSources();

    @ApiOperation("更新数据源信息")
    @PostMapping("/datasource/update")
    Result<String> updateDataSource(DataSourceInputDto dataSourceInputDto);

    @ApiOperation("添加数据源信息")
    @PutMapping("/datasource/create")
    Result<DataSourceOutputDto> createDataSource(DataSourceInputDto dataSourceInputDto);

    @ApiOperation("删除数据源信息")
    @DeleteMapping("/datasource/delete")
    Result<String> deleteDataSource(DataSourceInputDto dataSourceInputDto);
}