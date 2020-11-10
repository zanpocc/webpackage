package com.zanpo.it.entity;

import lombok.Data;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/30 23:59
 */
@Data
public class Column {
    private String name;
    // 建表语句中的类型加大小
    private String type;
    private String comment;

    private String nullAble;
    // 建表语句中的类型
    private String dataType;
}
