package com.zanpo.it.dto.directory;

import lombok.Data;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 20:58
 */
@Data
public class PathNodeOutputDto {

    private int id;

    private String label;

    List<PathNodeOutputDto> children;
}
