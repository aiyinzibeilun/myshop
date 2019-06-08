package com.myshop.web.admin.interceptor;

import com.myshop.common.domain.TbUser;
import com.myshop.common.utils.CookieUtils;
import com.myshop.common.utils.MapperUtils;
import com.myshop.common.web.utils.HttpServletUtils;
import com.myshop.web.admin.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author yang
 * @Date 2019/6/4
 */
public class WebAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;
    @Value("sso.host")
    private String sso_host;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "token");
        //没有登陆
        if(StringUtils.isBlank(token)){
            //跳转到单点登录
            response.sendRedirect(String.format("%s/login?url=%s",sso_host,HttpServletUtils.getFullPath(request)));
            return false;
        }
        return true;
    }

    /**
     * 创建局部会话，让对象一直保留到session中
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TbUser tbUser=new TbUser();
        HttpSession session = request.getSession();
        TbUser user = (TbUser) session.getAttribute("user");
        //已登陆
        if (user != null) {
            if (modelAndView != null) {
                modelAndView.addObject("user",user);
            }
        }
        //未登录
        else {
            String token = CookieUtils.getCookieValue(request, "token");
            if(StringUtils.isNotBlank(token)){
                //根据token获取redis中的用户名
                String logincode = redisService.get(token);
                if (logincode != null) {
                    //根据用户名获取对象
                    String s = redisService.get(logincode);
                    if(StringUtils.isNotBlank(s)){
                        //创建局部会话
                         tbUser = MapperUtils.obj2pojo(s, TbUser.class);
                        if (modelAndView != null) {
                            modelAndView.addObject("user",user);
                        }
                        request.getSession().setAttribute("user",tbUser);

                    }

                }
                //二次确认是否有登陆状态，存在seseion失效的问题（设置了过期时间）

                if (tbUser == null) {
                    //跳转到单点登录
                    response.sendRedirect(String.format("%s/login?url=%s",sso_host,HttpServletUtils.getFullPath(request)));
                }
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
