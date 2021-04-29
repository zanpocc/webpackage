package com.zanpo.it.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zanpo.it.api"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(setHeaderParameter());

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("webpackage APIs")
                .description("接口文档")
                .version("1.0")
                .build();
    }

    /*
     *  默认参数
     */
    private List<Parameter> setHeaderParameter() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder langPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("AuthToken").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        langPar.name("lang").description("lang").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(langPar.build());
        return pars;
    }
}
