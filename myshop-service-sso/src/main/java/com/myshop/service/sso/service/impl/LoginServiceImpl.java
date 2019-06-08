package com.myshop.service.sso.service.impl;

import com.myshop.common.domain.TbUser;
import com.myshop.common.utils.MapperUtils;
import com.myshop.service.sso.mapper.TbUserMapper;
import com.myshop.service.sso.service.LoginService;
import com.myshop.service.sso.service.comsumer.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;



/**
 * @Author yang
 * @Date 2019/6/3
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TbUserMapper tbUserMapper;
    @Override
    public TbUser login(String username, String pwd) {
        //查看缓存
        String o = redisService.get(username);
        //没有缓存查数据库
        if (o == null) {
            Example example=new Example(TbUser.class);
            example.createCriteria().andEqualTo("username",username);
            TbUser tbUser = (TbUser) tbUserMapper.selectOneByExample(example);
            if(tbUser!=null){
                String password = DigestUtils.md5DigestAsHex(pwd.getBytes());
                if(tbUser.getPassword().equals(password)){
                    try {
                        redisService.put(username,MapperUtils.obj2json(tbUser),24*60*60);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return tbUser;
                }
            }else {
                return null;
            }
//            缓存中有数据
        }else {
            try {
                TbUser tbUser = MapperUtils.json2pojo(o, TbUser.class);
                return tbUser;
            } catch (Exception e) {
                log.warn("触法熔断{}",e.getMessage());
            }

        }

        return null;
    }
}
