package com.myshop.service.redis.service.impl;

import com.myshop.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author yang
 * @Date 2019/6/3
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void put(String key, Object value, long second) {
        redisTemplate.opsForValue().set(key,value,second,TimeUnit.SECONDS);

    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
