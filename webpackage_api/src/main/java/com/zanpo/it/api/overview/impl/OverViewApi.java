package com.zanpo.it.api.overview.impl;

import com.zanpo.it.api.overview.IOverViewApi;
import com.zanpo.it.app.overview.IOverViewApp;
import com.zanpo.it.dto.overview.SystemInfoOutputDto;
import com.zanpo.it.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 概览接口API层实现
 *
 * @author cg
 * @date 2020/10/28 20:23
 */
@RestController
public class OverViewApi implements IOverViewApi {

    @Autowired
    IOverViewApp overViewApp;

    @Override
    public Result<SystemInfoOutputDto> getSystemInfo() {
        SystemInfoOutputDto systemInfo = overViewApp.getSystemInfo();
        return Result.success(systemInfo);
    }
}
