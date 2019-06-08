package com.myshop.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.myshop.common.domain.TbUser;
import com.myshop.common.dto.BaseResult;
import com.myshop.common.utils.MapperUtils;
import com.myshop.service.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author yang
 * @Date 2019/6/4
 */
@Controller
@RequestMapping("/v1/admins")
@Api("后台服务提供者")
public class ServiceAdminController {
    @Autowired
    private UserService<TbUser> userService;

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param tbUserJson
     * @return
     */
    @ApiOperation("分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "pageSize",value = "数量",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "tbUserJson",value = "json对象",required = false,dataTypeClass = String.class,paramType = "json")
    }

    )
    @RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true)int pageSize,
            @RequestParam(required = false)String tbUserJson){
        TbUser tbUser=null;
        if (tbUserJson != null) {
            try {
                 tbUser = MapperUtils.json2pojo(tbUserJson, TbUser.class);
            } catch (Exception e) {

            }
        }
        PageInfo page = userService.page(pageNum, pageSize, tbUser);
        //分页之后
        List list = page.getList();

        //封装curse对象
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(page.getTotal()).intValue());
        cursor.setOffset(page.getPageNum());
        cursor.setLimit(page.getPageSize());
        return BaseResult.ok(list,cursor);

    }

    /**
     * 根据username获取对象
     * @param username
     * @return
     */
    @RequestMapping(value = "{username}",method = RequestMethod.GET)
    public BaseResult get(@PathVariable("username")String username){
        TbUser tbUser = new TbUser();
        tbUser.setUsername(username);
        TbUser tbUser1 = userService.selectOne(tbUser);
        return BaseResult.ok(tbUser1);
    }

    /**
     * 保存包括新增和修改
     * @param userJson
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public BaseResult save(
            @RequestParam(required = true)String userJson){
        int result=0;
        TbUser tbUser=null;
        try {
             tbUser = MapperUtils.json2pojo(userJson, TbUser.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (tbUser != null) {
            //主键为空表示新增
            if (tbUser.getId() == null) {
                 result = userService.insert(tbUser);
            }
            //其他表示修改
            else{
                 result = userService.update(tbUser);
            }
            if(result>0){
                return BaseResult.ok("保存成功！");
            }
        }
        return BaseResult.ok("保存失败");
    }

}
