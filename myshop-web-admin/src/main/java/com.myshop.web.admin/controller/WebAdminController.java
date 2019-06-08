package com.myshop.web.admin.controller;

import com.myshop.web.admin.service.ServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author yang
 * @Date 2019/5/31
 */
@Controller
public class WebAdminController {
    @Autowired
    private ServiceAdmin serviceAdmin;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){

        return "index";
    }

}
