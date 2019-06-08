package com.myshop.service.sso.service;

import com.myshop.common.domain.TbUser;

/**
 * 单点登陆服务提供者
 * @Author yang
 * @Date 2019/6/3
 */
public interface LoginService {
    public TbUser login(String username, String pwd);
}
