package com.myshop.service.redis.service;

/**
 * redis的共有接口
 * @Author yang
 * @Date 2019/6/3
 */
public interface RedisService {
    /**
     *
     * @param key
     * @param value
     * @param second 超时时间
     */
    public void put(String key,Object value,long second);

    public Object get(String key);

}
