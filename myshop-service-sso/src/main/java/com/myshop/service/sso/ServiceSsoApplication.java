package com.myshop.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 既是单点登陆的服务提供者,又是redis的服务的消费者
 *
 * //@SpringBootApplication默认是扫描主配置类所在的包
 *
 *由于需要使用myshop-common-web下面的拦截器，可以使用scanBasePackages
 *
 * @Author yang
 * @Date 2019/5/30
 */

@SpringBootApplication(scanBasePackages = "com.myshop")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.myshop.service.sso.mapper")
public class ServiceSsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSsoApplication.class, args);
    }
}
