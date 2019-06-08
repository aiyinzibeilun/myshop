package com.myshop.service.admin.service.impl;


import com.github.pagehelper.PageInfo;
import com.myshop.common.domain.TbUser;
import com.myshop.common.mapper.TbUserMapper;
import com.myshop.common.service.impl.BaseServiceImpl;
import com.myshop.service.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

/**
 *
 * 继承通用的extends BaseServiceImpl<TbUser,TbUserMapper>能获取通用增删改查
 *
 * @Author yang
 * @Date 2019/5/30
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<TbUser,TbUserMapper> implements UserService<TbUser> {
    @Autowired
    private  TbUserMapper tbUserMapper;

    @Override
    public void register(TbUser tbUser) {
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUserMapper.insert(tbUser);
    }

    @Override
    public TbUser login(String username, String pwd) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);
        TbUser tbUser = (TbUser) tbUserMapper.selectOneByExample(example);
        if (tbUser != null) {
            String password = DigestUtils.md5DigestAsHex(pwd.getBytes());
            if (tbUser.getPassword().equals(password)) {
                return tbUser;
            }
        }
        return null;
    }

}
