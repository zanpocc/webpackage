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
    private String type;
    private String key;
    private String comment;
    private String nullAble;
}
