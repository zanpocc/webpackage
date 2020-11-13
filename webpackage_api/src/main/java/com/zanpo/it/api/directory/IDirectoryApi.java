package com.zanpo.it.api.directory;

import com.zanpo.it.dto.directory.PathNodeOutputDto;
import com.zanpo.it.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 20:54
 */
@Api(tags = "本地目录API")
@RequestMapping("/directory")
@RestController
public interface IDirectoryApi {
    @ApiOperation("列举指定目录下所有子目录")
    @GetMapping("/directory/list/")
    Result<List<PathNodeOutputDto>> getChildrenDirs(@RequestParam("path") String path);
}
