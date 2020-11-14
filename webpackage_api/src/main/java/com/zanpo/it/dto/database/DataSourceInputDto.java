package com.zanpo.it.dto.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:52
 */
@Data
@ApiModel
public class DataSourceInputDto {
    @ApiModelProperty(value = "JDBC连接串,默认本地3306")
    String url = "jdbc:mysql://localhost:3306";
    @ApiModelProperty(value = "数据库连接账户,默认root")
    String user = "root";
    @ApiModelProperty(value = "数据库连接密码,默认123456")
    String password = "123456";
}
