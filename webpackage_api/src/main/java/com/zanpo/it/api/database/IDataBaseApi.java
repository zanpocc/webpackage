package com.zanpo.it.api.database;

import com.zanpo.it.dto.database.CodeGenInputDto;
import com.zanpo.it.dto.database.DataSourceInputDto;
import com.zanpo.it.dto.database.DataSourceOutputDto;
import com.zanpo.it.dto.database.TableOutputDto;
import com.zanpo.it.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库接口API接口
 *
 * @author cg
 * @date 2020/10/29 19:51
 */
@Api(tags = "数据库接口API")
@RequestMapping("/database")
public interface IDataBaseApi {

    @ApiOperation(value = "列举所有数据源信息", notes = "列举所有数据源信息")
    @GetMapping("/datasource/list")
    Result<List<DataSourceOutputDto>> findDataSources();

    @ApiOperation(value = "更新数据源信息", notes = "更新数据源信息")
    @PostMapping("/datasource/update")
    Result<String> updateDataSource(DataSourceInputDto dataSourceInputDto);

    @ApiOperation(value = "添加数据源信息", notes = "添加数据源信息")
    @PutMapping("/datasource/create")
    Result<DataSourceOutputDto> createDataSource(DataSourceInputDto dataSourceInputDto);

    @ApiOperation(value = "删除数据源信息", notes = "删除数据源信息")
    @DeleteMapping("/datasource/delete")
    Result<String> deleteDataSource(DataSourceInputDto dataSourceInputDto);

    @ApiOperation(value = "查询指定schema的所有表信息", notes = "查询指定schema的所有表信息")
    @GetMapping("/table/list/{schema}")
    Result<List<TableOutputDto>> findAllTables(@PathVariable("schema") String schema);

    @ApiOperation(value = "根据外键命名规则生成外健添加SQL", notes = "根据外键命名规则生成外健添加SQL")
    @GetMapping("/table/foreign/generate/{schema}")
    Result<String> generateForeignKey(@PathVariable("schema") String schema);

    @ApiOperation(value = "生成数据库的Mybatis代码", notes = "生成数据库的Mybatis代码")
    @PostMapping("/codeGen")
    Result<String> generateCode(@RequestBody CodeGenInputDto codeGenInputDto);

    @ApiOperation(value = "查找数据库中的schema", notes = "查找数据库中的schema")
    @GetMapping("/schema/list")
    Result<List<String>> findAllSchema();
}
