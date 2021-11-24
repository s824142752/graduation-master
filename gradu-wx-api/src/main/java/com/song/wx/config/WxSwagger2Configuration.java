package com.song.wx.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger在线文档配置<br>
 * 项目启动后可通过地址：http://host:ip/swagger-ui.html 查看在线文档
 *
 */

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class WxSwagger2Configuration {
    @Bean
    public Docket wxDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("wx")
                .apiInfo(wxApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.song.wx.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo wxApiInfo() {
        return new ApiInfoBuilder()
                .title("gradu-wx API")
                .description("毕设小程序API")
                .termsOfServiceUrl("https://github.com")
                .contact("https://github.com")
                .version("1.0")
                .build();
    }
}
