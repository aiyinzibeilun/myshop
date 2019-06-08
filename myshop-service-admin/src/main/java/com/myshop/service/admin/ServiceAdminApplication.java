package com.myshop.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author yang
 * @Date 2019/5/30
 *
 * 需要扫描 1、可以每个项目独立的mapper： com.myshop.service.admin.mapper
 *          2、通用的mapper： com.myshop.common.mapper
 *
 * 扫描其他项目的注解  @SpringBootApplication(scanBasePackages = "com.myshop")
 */

@SpringBootApplication(scanBasePackages = "com.myshop")
@EnableEurekaClient
@MapperScan(basePackages = {"com.myshop.service.admin.mapper","com.myshop.common.mapper"})//要导tk
@EnableSwagger2
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
