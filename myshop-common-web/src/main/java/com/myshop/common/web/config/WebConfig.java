package com.myshop.common.web.config;

import com.myshop.common.web.intercept.ConstantsIntercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yang
 * @Date 2019/6/4
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public ConstantsIntercept constantsIntercept(){
        return new ConstantsIntercept();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(constantsIntercept()).addPathPatterns("/**");
    }
}
