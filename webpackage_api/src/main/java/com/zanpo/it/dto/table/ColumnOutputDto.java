package com.zanpo.it.dto.table;

import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/1 21:15
 */
@Data
public class ColumnOutputDto {
    private String name;
    private String type;
    private String key;
    private String comment;
    private String nullAble;
    // 建表语句中的类型
    private String dataType;
}
