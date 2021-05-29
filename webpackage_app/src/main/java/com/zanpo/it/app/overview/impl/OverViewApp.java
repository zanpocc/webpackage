package com.zanpo.it.app.overview.impl;

import com.zanpo.it.app.overview.IOverViewApp;
import com.zanpo.it.dto.overview.SystemInfoOutputDto;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Set;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/28 20:26
 */
@Component
public class OverViewApp implements IOverViewApp {
    @Override
    public SystemInfoOutputDto getSystemInfo() {
        SystemInfoOutputDto systemInfoOutputDto = new SystemInfoOutputDto();

        Properties properties = System.getProperties();
        Set<Object> keys = properties.keySet();
        for(Object key : keys){
            if("os.name".equals(key)){
                systemInfoOutputDto.setOsName((String) properties.get(key));
            }else if("os.version".equals(key)){
                systemInfoOutputDto.setOsVersion((String) properties.get(key));
            }else if("user.name".equals(key)){
                systemInfoOutputDto.setUserName((String) properties.get(key));
            }
        }
        return systemInfoOutputDto;
    }
}
