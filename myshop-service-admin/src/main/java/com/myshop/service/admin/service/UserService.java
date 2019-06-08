package com.myshop.service.admin.service;


import com.myshop.common.domain.BaseDomain;
import com.myshop.common.domain.TbUser;
import com.myshop.common.service.BaseService;


/**
 * @Author yang
 * @Date 2019/5/30
 */
public interface UserService<T extends BaseDomain> extends BaseService<T> {
    public TbUser login(String username,String pwd);
    public void register(TbUser tbUser);
}
