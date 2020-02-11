package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;

import java.util.List;

public interface RoleManagementService {

    public PageResult pageQuery(QueryPageBean queryPageBean);
    public List<Permission> findAll();
    public List<Integer> findPermissionIdsByRoleId(Integer id);
    public void add(Role role,Integer[] permissionIds);
}
