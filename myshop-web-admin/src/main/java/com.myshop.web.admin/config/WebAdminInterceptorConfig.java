package com.myshop.web.admin.config;

import com.myshop.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author yang
 * @Date 2019/6/4
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {
    @Bean
    WebAdminInterceptor webAdminInterceptor(){
        return new WebAdminInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webAdminInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static");
    }
}
