package com.myshop.common.service;

import com.github.pagehelper.PageInfo;
import com.myshop.common.domain.BaseDomain;

/**
 * 通用的增删改查
 *
 * @Author yang
 * @Date 2019/6/4
 */
public interface BaseService<T extends BaseDomain> {
    int insert(T t);
    int delete(T t);
    int update(T t);
    int count(T t);
    T selectOne(T t);

    PageInfo<T> page(int pageNum,int pageSize,T t);
}
