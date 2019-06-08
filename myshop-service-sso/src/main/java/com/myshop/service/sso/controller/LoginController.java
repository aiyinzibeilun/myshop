package com.myshop.service.sso.controller;

import com.myshop.common.domain.TbUser;
import com.myshop.common.utils.CookieUtils;
import com.myshop.common.utils.MapperUtils;
import com.myshop.service.sso.service.LoginService;
import com.myshop.service.sso.service.comsumer.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author yang
 * @Date 2019/6/3
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url,HttpServletRequest request,Model model){
        //查看cookie中有没有token
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isNotBlank(token)){
            //根据token获取redis中的用户名
            String logincode = redisService.get(token);
            if (logincode != null) {
                //根据用户名获取对象
                String s = redisService.get(logincode);
                if(StringUtils.isNotBlank(s)){
                    TbUser tbUser = MapperUtils.obj2pojo(s, TbUser.class);
                    //已登陆
                    if(StringUtils.isNotBlank(url)){
                        return "redirect:"+url;
                    }
                    model.addAttribute("user",tbUser);
                }

            }


        }
        if (url != null) {
            model.addAttribute("url",url);
        }
        return "login";
    }

    /**
     *登陆业务
     * @param username
     * @param pwd
     * @param url 由于存在多个系统，针对不同的登陆系统，返回对应的系统
     *             @RequestParam(required = false)不是必须的，可传可不传
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required =true) String username,
                        @RequestParam(required =true)String pwd,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes attributes) {
        TbUser tbUser = loginService.login(username, pwd);
        //登陆失败
        if (tbUser == null) {
            attributes.addFlashAttribute("message","用户名或者密码输入错误");
        }
        //登陆成功
        else {
            String token = UUID.randomUUID().toString();
//            将token放入cookie
            String result = redisService.put(token, username, 24 * 60 * 60);
            if (StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request,response,"token",token,24 * 60 * 60);
                if (StringUtils.isNotBlank(url)) {
                    return "redirect:"+url;
                }
            }
            //redis服务熔断
            else{
                attributes.addAttribute("message","服务异常稍后重试");
            }

        }
        return "redirct:/login";
    }

    /**
     * 注销
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam(required = false) String url,
                         Model model){
        CookieUtils.deleteCookie(request,response,"token");
        //可能不是从单点登录注销
        return login(url,request,model);
    }
}
