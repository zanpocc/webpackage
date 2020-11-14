package com.zanpo.it.api.directory.impl;

import com.zanpo.it.api.directory.IDirectoryApi;
import com.zanpo.it.app.directory.IDirectoryApp;
import com.zanpo.it.dto.directory.PathNodeOutputDto;
import com.zanpo.it.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 20:59
 */
@Component
public class DirectoryApi implements IDirectoryApi {
    @Autowired
    IDirectoryApp directoryApp;

    @Override
    public Result<List<PathNodeOutputDto>> getChildrenDirs(String path) {
        List<PathNodeOutputDto> result = directoryApp.getChildrenDirs(path);
        return Result.success(result);
    }
}
