package com.myshop.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myshop.common.domain.BaseDomain;
import com.myshop.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

/**
 * @Author yang
 * @Date 2019/6/4
 */
@Service
@Transactional(readOnly = true)
public class BaseServiceImpl<T extends BaseDomain,D extends MyMapper<T>> implements BaseService<T> {
    @Autowired
    private D dao;
    @Transactional(readOnly = false)
    @Override
    public int insert(T t) {
        return dao.insert(t);
    }
    @Transactional(readOnly = false)
    @Override
    public int delete(T t) {
        return dao.delete(t);
    }
    @Transactional(readOnly = false)
    @Override
    public int update(T t) {
        return dao.updateByPrimaryKey(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize,T t) {
        //采用分页插件进行分页
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<T> tPageInfo = new PageInfo<>(dao.select(t));
        return tPageInfo;
    }
}
