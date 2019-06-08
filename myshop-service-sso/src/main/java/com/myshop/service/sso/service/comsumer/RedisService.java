package com.myshop.service.sso.service.comsumer;

import com.myshop.service.sso.service.comsumer.fallback.RedisFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * redis的服务消费者
 * @Author yang
 * @Date 2019/6/3
 */

@FeignClient(value = "myshop-service-redis",fallback = RedisFallback.class)
public interface RedisService {
    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(@RequestParam("key")String key,@RequestParam("value")String value,@RequestParam("seconds")long seconds );
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(@RequestParam("key")String key);
}
