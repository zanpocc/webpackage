package com.zanpo.it.app.directory;

import com.zanpo.it.dto.directory.PathNodeOutputDto;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 21:01
 */
public interface IDirectoryApp {
    List<PathNodeOutputDto> getChildrenDirs(String path);
}
