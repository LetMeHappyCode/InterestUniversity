package com.interest.common.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("appApi")
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/app/.*")))
                .build();

    }
    private ApiInfo adminApiInfo(){

        return new ApiInfoBuilder()
                .title("兴趣信息系统-API文档")
                .description("本文档描述了兴趣信息系统接口")
                .version("1.0")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
    }


    @Bean
    public Docket adapterApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adapter")
                .apiInfo(adapterApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/adapter/.*")))
                .build();

    }
    private ApiInfo adapterApiInfo(){

        return new ApiInfoBuilder()
                .title("适配层-API文档")
                .description("适配层系统接口")
                .version("1.0")
                .contact(new Contact("Helen", "http://atguigu.com", "55317332@qq.com"))
                .build();
    }

}
