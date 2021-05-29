package com.zanpo.it.utils;

import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring容器工具类
 *
 * @author cg
 * @date 2020/10/30 00:55
 */
@Component
public final class SpringUtils implements ApplicationContextAware {

    @Getter
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 初始化时得到applicationContext对象
        if(SpringUtils.applicationContext == null){
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtils.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringUtils.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringUtils.applicationContext.getEnvironment().getProperty(key);
    }
}
