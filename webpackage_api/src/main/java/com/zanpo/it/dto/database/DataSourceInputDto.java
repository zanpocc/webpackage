package com.zanpo.it.dto.database;

import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 01:52
 */
@Data
public class DataSourceInputDto {
    String url = "jdbc:mysql://localhost:3306";
    String user = "root";
    String password = "123456";
}
