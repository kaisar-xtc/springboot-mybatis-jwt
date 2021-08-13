package com.hsrd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("xtc")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hsrd.controller"))
                .build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("xtc","http://localhost:8080/hello","1456546497@qq.com");
        return new ApiInfo(
                "first swagger api document",
                "这是一个接口说明document",
                "v1.0",
                "http://localhost:8080/hello",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
