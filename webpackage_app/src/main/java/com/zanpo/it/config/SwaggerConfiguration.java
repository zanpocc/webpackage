package com.zanpo.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Swagger配置类
 *
 * @author cg
 * @date 2020/10/28 21:35
 */
@Configuration
@EnableSwagger2
@SuppressWarnings("unchecked")
public class SwaggerConfiguration {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfo(
                "WebPackage API文档",    //标题
                "相关接口信息的swagger描述",         //描述
                "v1.0",                   //版本
                "",              //服务条款URL
                new Contact("Zanpocc", "http://www.github.com/zanpocc", "zanpocc@gmail.com") ,   //作者信息
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        ));
    }
}
