package com.zanpo.it.api.overview;

import com.zanpo.it.dto.overview.SystemInfoOutputDto;
import com.zanpo.it.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 概览页API层接口
 *
 * @author cg
 * @date 2020/10/28 20:22
 */
@Api(tags = "概览API")
@RequestMapping("/overview")
@RestController
public interface IOverViewApi {

    @ApiOperation("查询系统详细信息")
    @GetMapping("/overview/detail")
    Result<SystemInfoOutputDto> getSystemInfo();
}
