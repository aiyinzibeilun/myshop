package com.myshop.web.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author yang
 * @Date 2019/5/30
 */

@SpringBootApplication(scanBasePackages = "com.myshop")
@EnableEurekaClient
@EnableDiscoveryClient
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class, args);
    }
}
