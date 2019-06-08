package com.myshop.web.admin.service.fallback;


import com.myshop.web.admin.service.RedisService;
import org.springframework.stereotype.Component;

/**
 * redis的服务熔断
 *
 * @Author yang
 * @Date 2019/6/3
 */
@Component
public class RedisFallback implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
    return null;

    }

    @Override
    public String get(String key) {
        return null;
    }
}
