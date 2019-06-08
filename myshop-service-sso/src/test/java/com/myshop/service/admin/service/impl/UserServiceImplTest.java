package com.myshop.service.admin.service.impl;


import com.myshop.service.admin.ServiceAdminApplication;
import com.myshop.service.admin.domain.TbUser;
import com.myshop.service.admin.mapper.TbUserMapper;
import com.myshop.service.admin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author yang
 * @Date 2019/5/31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void register() {
        TbUser tbUser = new TbUser();
        tbUser.setPassword("123456");
        tbUser.setUsername("zs");
        tbUser.setCreated(new Date());
        tbUser.setEmail("123@qq.com");
        tbUser.setId(1L);
        tbUser.setPhone("12345678");
        tbUser.setUpdated(new Date());
        userService.register(tbUser);
    }

    @Test
    public void login() {
        TbUser zs = userService.login("zs", "123456");
        System.out.println(zs);
    }
}
