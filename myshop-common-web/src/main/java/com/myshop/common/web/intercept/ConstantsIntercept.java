package com.myshop.common.web.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器解决静态资源问题
 *
 * @Author yang
 * @Date 2019/6/4
 */
public class ConstantsIntercept implements HandlerInterceptor {
    private static final String HOST_CDN="http://192.168.47.128";

    private  static final String TEMPLEATE_ADMIN="adminlte/v2.4.3";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 前端页面添加cdn地址
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            modelAndView.addObject("adminlte",HOST_CDN+"/"+TEMPLEATE_ADMIN);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
