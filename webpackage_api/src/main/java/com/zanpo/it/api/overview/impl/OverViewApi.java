package com.zanpo.it.api.overview.impl;

import com.zanpo.it.api.overview.IOverViewApi;
import com.zanpo.it.appapi.overview.IOverViewApp;
import com.zanpo.it.dto.overview.SystemInfoOutputDto;
import com.zanpo.it.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 概览接口API层实现
 *
 * @author cg
 * @date 2020/10/28 20:23
 */
@Component
public class OverViewApi implements IOverViewApi {

    @Autowired
    IOverViewApp overViewApp;

    public Result<SystemInfoOutputDto> getSystemInfo() {
        SystemInfoOutputDto systemInfo = overViewApp.getSystemInfo();
        return Result.success(systemInfo);
    }
}
