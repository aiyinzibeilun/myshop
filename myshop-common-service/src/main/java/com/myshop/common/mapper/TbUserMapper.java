package com.myshop.common.mapper;

import com.myshop.common.domain.TbUser;
import com.myshop.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

/**
 * 在接口出开启缓存，使用自己定义的RedisCache
 */
@CacheNamespace(implementation = RedisCache.class)
public interface TbUserMapper extends MyMapper<TbUser> {
}