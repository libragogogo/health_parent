package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.service.RoleManagementService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roleManagement")
public class RoleManagementController {

    @Reference//查找服务
    private RoleManagementService roleManagementService;

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = roleManagementService.pageQuery(queryPageBean);
        return pageResult;
    }


    //查询所有检查组
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<Permission> list = roleManagementService.findAll();
            return new Result(true, MessageConstant.QUERY_PERMISSION_SUCCESS,list);//查询成功
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_FAIL);//查询失败
        }
    }



    //根据检查组ID查询检查组包含的多个检查项ID
    @RequestMapping("/findPermissionIdsByRoleId")
    public Result findPermissionIdsByRoleId(Integer id){
        try{
            List<Integer> permissionIds = roleManagementService.findPermissionIdsByRoleId(id);
            return new Result(true,MessageConstant.QUERY_PERMISSION_ID_SUCCESS,permissionIds);//查询成功
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_PERMISSION_ID_FAIL);//查询失败
        }
    }


    //编辑检查组
    @RequestMapping("/add")
    public Result add(@RequestBody Role role,Integer[] permissionIds){
        Integer roleId = role.getId();
        System.out.println("roleId = " + roleId);
        try{
            roleManagementService.add(role,permissionIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_PERMISSION_ID_FAIL);//新增失败
        }
        return new Result(true,MessageConstant.ADD_PERMISSION_ID_SUCCESS);//新增成功
    }

}
