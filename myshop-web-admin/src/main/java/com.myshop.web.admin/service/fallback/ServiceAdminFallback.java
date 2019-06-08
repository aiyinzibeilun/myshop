package com.myshop.web.admin.service.fallback;

import com.myshop.common.hystrix.Fallback;
import com.myshop.web.admin.service.ServiceAdmin;
import org.springframework.stereotype.Component;


/**
 *
 * @Author yang
 * @Date 2019/5/31
 */
@Component
public class ServiceAdminFallback implements ServiceAdmin {
    @Override
    public String login(String username, String password) {
        return Fallback.badGatway();
    }
}
