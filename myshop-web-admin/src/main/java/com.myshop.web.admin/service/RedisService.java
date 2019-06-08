package com.myshop.web.admin.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * redis的共有接口
 * @Author yang
 * @Date 2019/6/3
 */
@FeignClient(value = "myshop-service-admin",fallback = RedisService.class)
public interface RedisService {
    /**
     *
     * @param key
     * @param value
     * @param second 超时时间
     */
    public String put(String key, String value, long second);

    public String get(String key);

}
