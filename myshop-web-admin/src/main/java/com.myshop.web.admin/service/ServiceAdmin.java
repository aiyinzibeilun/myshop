package com.myshop.web.admin.service;

import com.myshop.web.admin.service.fallback.ServiceAdminFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * 服务消费者
 * @Author yang
 * @Date 2019/5/31
 */
@FeignClient(value = "myshop-service-admin",fallback = ServiceAdminFallback.class)
public interface ServiceAdmin {
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(@RequestParam("username")String username,@RequestParam("password") String password);
}
