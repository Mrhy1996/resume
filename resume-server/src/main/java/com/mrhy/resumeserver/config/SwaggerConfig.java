package com.mrhy.resumeserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
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
 * swagger 配置类
 *
 * @author mrhy
 * @date 2020/10/6 11:00 下午
 * Copyright (C), 2018-2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger-flag}")
    boolean flag;

    @Bean
    public Docket docket() {
        //header中的ticket参数非必填，传空也可以
        //根据每个方法名也知道当前方法在设置什么参数
//        ParameterBuilder authorityPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        authorityPar.name("Authority").description("Authority")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                .required(false).build();
//        pars.add(authorityPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
//                .globalOperationParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrhy.resumeserver.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("", "", "");
        return new ApiInfo("微服务", "微服务", "1.0",
                "urn:tos", contact, "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
