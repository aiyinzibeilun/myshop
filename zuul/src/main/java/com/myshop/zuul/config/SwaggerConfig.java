package com.myshop.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableSwagger2

public class SwaggerConfig {
    @Autowired
    private ZuulProperties zuulProperties;
    @Primary
    @Bean
    public SwaggerResourcesProvider resourcesProvider(){
        return new SwaggerResourcesProvider(){
            @Override
            public List<SwaggerResource> get() {
                List<SwaggerResource> resources=new ArrayList<SwaggerResource>();
                Map<String, ZuulProperties.ZuulRoute> routes = zuulProperties.getRoutes();
                Collection<ZuulProperties.ZuulRoute> values = routes.values();
                for (ZuulProperties.ZuulRoute value : values) {
                    resources.add(create(value.getServiceId(),value.getPath().replace("**","v2/api-docs"),"2.0"));
                }
                return resources;
            }
        };
    }
    private SwaggerResource create(String name,String location,String version){
        SwaggerResource resource=new SwaggerResource();
        resource.setLocation(location);
        resource.setName(name);
        resource.setSwaggerVersion(version);
        return resource;
    }

}
