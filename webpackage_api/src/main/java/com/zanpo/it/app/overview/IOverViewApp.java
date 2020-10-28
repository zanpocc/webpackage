package com.zanpo.it.app.overview;

import com.zanpo.it.dto.overview.SystemInfoOutputDto;

/**
 * 概览页APP层接口
 *
 * @author cg
 * @date 2020/10/28 20:24
 */
public interface IOverViewApp {
    SystemInfoOutputDto getSystemInfo();
}
