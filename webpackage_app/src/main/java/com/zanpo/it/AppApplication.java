package com.zanpo.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/10/28 20:11
 */
@SpringBootApplication
public class AppApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
