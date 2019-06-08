package com.myshop.service.redis.controller;

import com.myshop.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yang
 * @Date 2019/6/4
 */
@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(String  key){
        Object o = redisService.get(key);
        if (o != null) {
            String s = String.valueOf(o);
            return s;
        }
        return null;
    }
    @RequestMapping(value = "put",method = RequestMethod.POST)
    public String put(String key,String value,long seconds){
        redisService.put(key,value,seconds);
        return "ok";
    }
}
