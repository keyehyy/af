package com.gt.af.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @desc 错误页面配置
 * @author zhukeyan
 * @date
 */

@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        WebServerFactoryCustomizer<ConfigurableWebServerFactory> webCustomizer = new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                ErrorPage[] errorPages = new ErrorPage[] {
                        new ErrorPage(HttpStatus.FORBIDDEN, "/403"),
                        new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                        new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"),
                };
                factory.addErrorPages(errorPages);
            }
        };
        return webCustomizer;
    }
}
