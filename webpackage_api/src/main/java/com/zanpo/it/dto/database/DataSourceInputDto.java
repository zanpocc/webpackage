package com.zanpo.it.dto.database;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:52
 */
@Data
public class DataSourceInputDto {
    @Parameter(description = "jdbc连接串")
    String url = "jdbc:mysql://localhost:3306";
    @Parameter(description = "连接账号")
    String user = "root";
    @Parameter(description = "连接密码")
    String password = "123456";
}
